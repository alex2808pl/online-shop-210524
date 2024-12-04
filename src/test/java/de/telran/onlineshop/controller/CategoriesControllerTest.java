package de.telran.onlineshop.controller;

import de.telran.onlineshop.dto.CategoryDto;
import de.telran.onlineshop.service.CategoriesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoriesController.class)
class CategoriesControllerTest {

    @Autowired
    private MockMvc mockMvc; // для иммитации запросов пользователей

    @MockBean
    private CategoriesService categoriesServiceMock;


    @Test
    void getAllCategories() throws Exception {
        when(categoriesServiceMock.getAllCategories()).thenReturn(List.of(new CategoryDto(1L,"Test")));
        this.mockMvc.perform(get("/categories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..categoryID").exists())
                .andExpect(jsonPath("$..categoryID").value(1))
                .andExpect(jsonPath("$..name").value("Test"))
        ;

    }

//    @Test
//    void getCategoryById() {
//    }
//
//    @Test
//    void getCategoryByName() {
//    }
//
//    @Test
//    void createCategories() {
//    }
//
//    @Test
//    void updateCategories() {
//    }
//
//    @Test
//    void deleteCategories() {
//    }
//
//    @Test
//    void handleTwoException() {
//    }
//
//    @Test
//    void handleException() {
//    }
}