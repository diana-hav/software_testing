package edu.havrysh;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConverterTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

        @Test
        void whenArabic_1_ThenRoman_I() {
            Assertions.assertEquals("I", Converter.arabicToRoman(1));
        }

        @Test
        void whenArabic_4_ThenRoman_IV() {
            Assertions.assertEquals("IV", Converter.arabicToRoman(4));
        }

        @Test
        void whenArabic_5_ThenRoman_V() {
            Assertions.assertEquals("V", Converter.arabicToRoman(5));
        }

        @Test
        void whenArabic_9_ThenRoman_IX() {
            Assertions.assertEquals("IX", Converter.arabicToRoman(9));
        }

        @Test
        void whenArabic_10_ThenRoman_X() {
            Assertions.assertEquals("X", Converter.arabicToRoman(10));
        }

        @Test
        void whenArabic_40_ThenRoman_XL() {
            Assertions.assertEquals("XL", Converter.arabicToRoman(40));
        }

        @Test
        void whenArabic_50_ThenRoman_L() {
            Assertions.assertEquals("L", Converter.arabicToRoman(50));
        }

        @Test
        void whenArabic_90_ThenRoman_XC() {
            Assertions.assertEquals("XC", Converter.arabicToRoman(90));
        }

        @Test
        void whenArabic_100_ThenRoman_C() {
            Assertions.assertEquals("C", Converter.arabicToRoman(100));
        }

        @Test
        void whenArabic_400_ThenRoman_CD() {
            Assertions.assertEquals("CD", Converter.arabicToRoman(400));
        }

        @Test
        void whenArabic_500_ThenRoman_D() {
            Assertions.assertEquals("D", Converter.arabicToRoman(500));
        }

        @Test
        void whenArabic_900_ThenRoman_CM() {
            Assertions.assertEquals("CM", Converter.arabicToRoman(900));
        }

        @Test
        void whenArabic_1000_ThenRoman_M() {
            Assertions.assertEquals("M", Converter.arabicToRoman(1000));
        }

        // Повторення символів у межах допустимого для тестування правильності
        @Test
        void whenArabic_2_ThenRoman_II() {
            Assertions.assertEquals("II", Converter.arabicToRoman(2));
        }

        @Test
        void whenArabic_3_ThenRoman_III() {
            Assertions.assertEquals("III", Converter.arabicToRoman(3));
        }

        @Test
        void whenArabic_6_ThenRoman_VI() {
            Assertions.assertEquals("VI", Converter.arabicToRoman(6));
        }

        @Test
        void whenArabic_11_ThenRoman_XI() {
            Assertions.assertEquals("XI", Converter.arabicToRoman(11));
        }

        @Test
        void whenArabic_20_ThenRoman_XX() {
            Assertions.assertEquals("XX", Converter.arabicToRoman(20));
        }

        @Test
        void whenArabic_30_ThenRoman_XXX() {
            Assertions.assertEquals("XXX", Converter.arabicToRoman(30));
        }

        @Test
        void whenArabic_60_ThenRoman_LX() {
            Assertions.assertEquals("LX", Converter.arabicToRoman(60));
        }

        @Test
        void whenArabic_1000_ThenRoman_M_Repeat() {
            Assertions.assertEquals("MMM", Converter.arabicToRoman(3000));
        }

        @Test
        void whenArabic_200_ThenRoman_CC() {
            Assertions.assertEquals("CC", Converter.arabicToRoman(200));
        }

        @Test
        void whenArabic_300_ThenRoman_CCC() {
            Assertions.assertEquals("CCC", Converter.arabicToRoman(300));
        }

        @Test
        void whenArabic_700_ThenRoman_DCC() {
            Assertions.assertEquals("DCC", Converter.arabicToRoman(700));
        }

        @Test
        void whenArabic_800_ThenRoman_DCCC() {
            Assertions.assertEquals("DCCC", Converter.arabicToRoman(800));
        }
    }