package edu.havrysh.lab5;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.havrysh.lab5.model.Item;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Long createdItemId;

    @BeforeEach
    void setUp() throws Exception {
        // Створюємо новий елемент для кожного тесту, щоб уникнути залежності від даних в базі
        Item item = new Item(null, "name-to-test", "000000", "description-t");

        MvcResult result = mockMvc.perform(post("/api/v1/items/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        Item created = objectMapper.readValue(response, Item.class);
        createdItemId = created.getId();
    }

    @Test
    @Order(1)
    void testCreateItem() throws Exception {
        Item item = new Item(null, "name-to-test", "000000", "description-t");

        MvcResult result = mockMvc.perform(post("/api/v1/items/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        Item created = objectMapper.readValue(response, Item.class);
        createdItemId = created.getId();
    }

    @Test
    @Order(2)
    void testGetAllItems() throws Exception {
        mockMvc.perform(get("/api/v1/items/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @Order(3)
    void testGetItemById() throws Exception {
        // Використовуємо динамічно створений елемент
        mockMvc.perform(get("/api/v1/items/" + createdItemId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createdItemId));
    }

    @Test
    @Order(4)
    void testUpdateItem() throws Exception {
        Item item = new Item(createdItemId, "name-updated", "000000", "description-t");

        mockMvc.perform(put("/api/v1/items/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("name-updated"));
    }

    @Test
    @Order(5)
    void testDeleteItem() throws Exception {
        // Видаляємо елемент, створений в setUp()
        mockMvc.perform(delete("/api/v1/items/" + createdItemId))
                .andExpect(status().isOk());
    }
}
