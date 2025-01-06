package de.telran.onlineshop.integration;

import de.telran.onlineshop.dto.CategoryDto;
import de.telran.onlineshop.repository.CategoriesRepository;
import de.telran.onlineshop.service.CategoriesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest //запускаем контейнер Spring
@AutoConfigureMockMvc(printOnlyOnFailure = false)
@ActiveProfiles(profiles = { "dev" })
public class CategoriesIntegrationTest {
    @Autowired
    private MockMvc mockMvc; // для иммитации запросов пользователей

    @MockBean
    private CategoriesRepository categoriesRepositoryMock;

    @Test
    void getAllCategoriesTest() throws Exception {
        this.mockMvc.perform(get("/categories"))
                .andDo(print()) //печать лога вызова
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..categoryID").exists())
                .andExpect(jsonPath("$..name").exists());
    }
}
