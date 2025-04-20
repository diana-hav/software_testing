package edu.havrysh.lab5;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@SpringBootTest
class Lab5ArchitectureTests {

    private JavaClasses applicationClasses;

    @BeforeEach
    void setUp() {
        applicationClasses = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("edu.havrysh.lab5");
    }

    @Test
    void controllersShouldResideInControllerPackage() {
        classes().that().areAnnotatedWith(RestController.class)
                .should().resideInAPackage("..controller..")
                .check(applicationClasses);
    }

    @Test
    void controllersShouldBeAnnotatedWithRestController() {
        classes().that().resideInAPackage("..controller..")
                .should().beAnnotatedWith(RestController.class)
                .check(applicationClasses);
    }

    @Test
    void serviceClassesShouldHaveServiceSuffix() {
        classes().that().resideInAPackage("..service..")
                .should().haveSimpleNameEndingWith("Service")
                .check(applicationClasses);
    }

    @Test
    void controllerClassesShouldHaveControllerSuffix() {
        classes().that().resideInAPackage("..controller..")
                .should().haveSimpleNameEndingWith("Controller")
                .check(applicationClasses);
    }

    @Test
    void repositoryInterfacesShouldHaveRepositorySuffix() {
        classes().that().resideInAPackage("..repository..")
                .should().haveSimpleNameEndingWith("Repository")
                .check(applicationClasses);
    }

    @Test
    void repositoryClassesShouldBeInterfaces() {
        classes().that().resideInAPackage("..repository..")
                .should().beInterfaces()
                .check(applicationClasses);
    }

    @Test
    void modelClassesShouldNotBePublicFields() {
        fields().that().areDeclaredInClassesThat().resideInAPackage("..model..")
                .should().notBePublic()
                .check(applicationClasses);
    }

    @Test
    void serviceClassesShouldNotBeAccessedByRepositories() {
        noClasses().that().resideInAPackage("..repository..")
                .should().dependOnClassesThat().resideInAPackage("..service..")
                .check(applicationClasses);
    }

    @Test
    void controllersShouldNotDependOnOtherControllers() {
        noClasses().that().resideInAPackage("..controller..")
                .should().dependOnClassesThat().resideInAPackage("..controller..")
                .check(applicationClasses);
    }

    @Test
    void layeredArchitectureShouldBeRespected() {
        ArchRuleDefinition
                .noClasses()
                .that()
                .resideInAPackage("..controller..")
                .should()
                .dependOnClassesThat()
                .resideInAPackage("..service..");

        ArchRuleDefinition
                .noClasses()
                .that()
                .resideInAPackage("..service..")
                .should()
                .dependOnClassesThat()
                .resideInAPackage("..repository..");

        ArchRuleDefinition
                .noClasses()
                .that()
                .resideInAPackage("..repository..")
                .should()
                .dependOnClassesThat()
                .resideInAPackage("..controller..");
    }


    @Test
    void modelClassesShouldNotBeFinal() {
        classes().that().resideInAPackage("..model..")
                .should().notHaveModifier(JavaModifier.FINAL)
                .check(applicationClasses);
    }

    @Test
    void noFieldsInControllerShouldBeAutowired() {
        noFields().that().areDeclaredInClassesThat().resideInAPackage("..controller..")
                .should().notBeAnnotatedWith(Autowired.class)
                .check(applicationClasses);
    }

    @Test
    void serviceShouldNotDependOnController() {
        noClasses().that().resideInAPackage("..service..")
                .should().dependOnClassesThat().resideInAPackage("..controller..")
                .check(applicationClasses);
    }

    @Test
    void repositoryShouldNotBeAccessedByController() {
        noClasses().that().resideInAPackage("..controller..")
                .should().accessClassesThat().resideInAPackage("..repository..")
                .check(applicationClasses);
    }

    @Test
    void serviceClassesShouldBeAnnotatedWithService() {
        classes().that().resideInAPackage("..service..")
                .should().beAnnotatedWith("org.springframework.stereotype.Service")
                .check(applicationClasses);
    }

    @Test
    void controllerClassesShouldNotBeFinal() {
        classes().that().resideInAPackage("..controller..")
                .should().notHaveModifier(JavaModifier.FINAL)
                .check(applicationClasses);
    }

    @Test
    void modelClassesShouldHavePrivateFields() {
        fields().that().areDeclaredInClassesThat().resideInAPackage("..model..")
                .should().bePrivate()
                .check(applicationClasses);
    }

    @Test
    void allControllersShouldBePublic() {
        classes().that().resideInAPackage("..controller..")
                .should().bePublic()
                .check(applicationClasses);
    }

    @Test
    void allModelClassesShouldResideInModelPackage() {
        classes().that().haveSimpleNameContaining("Item")
                .should().resideInAPackage("..controller..")
                .orShould().resideInAPackage("..repository..")
                .orShould().resideInAPackage("..service..")
                .orShould().resideInAPackage("..model..")
                .check(applicationClasses);
    }

    @Test
    void noCycleBetweenPackages() {
        slices().matching("edu.havrysh.lab5.(*)..")
                .should().beFreeOfCycles()
                .check(applicationClasses);
    }
}
