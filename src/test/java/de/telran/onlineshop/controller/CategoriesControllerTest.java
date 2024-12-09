package de.telran.onlineshop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.onlineshop.dto.CategoryDto;
import de.telran.onlineshop.service.CategoriesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoriesController.class)
class CategoriesControllerTest {

    @Autowired
    private MockMvc mockMvc; // для иммитации запросов пользователей

    @MockBean
    private CategoriesService categoriesServiceMock;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getAllCategories() throws Exception {
        when(categoriesServiceMock.getAllCategories()).thenReturn(List.of(new CategoryDto(1L,"Test")));
        this.mockMvc.perform(get("/categories"))
                .andDo(print()) //печать лога вызова
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..categoryID").exists())
                .andExpect(jsonPath("$..categoryID").value(1))
                .andExpect(jsonPath("$..name").value("Test"))
        ;

    }

    @Test
    void getCategoryById() throws Exception {
//        Long testId = 1L;
//        when(categoriesServiceMock.getCategoryById(testId)).thenReturn(new CategoryDto(testId,"Test"));
        when(categoriesServiceMock.getCategoryById(anyLong())).thenReturn(new CategoryDto(1L,"Test"));
        this.mockMvc.perform(get("/categories/find/{id}", 1)) ///categories/find/1
                .andDo(print()) //печать лога вызова
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryID").exists())
                .andExpect(jsonPath("$.categoryID").value(1))
                .andExpect(jsonPath("$.name").value("Test"));
    }

//    @Test
//    void getCategoryByName() {
//    }
//
    @Test
    void createCategories() throws Exception {
        when(categoriesServiceMock.createCategories(any(CategoryDto.class))).thenReturn(true);
        this.mockMvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "categoryID": null,
                            "name": "TestName"
                        }
                        """
                ))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void updateCategories() throws Exception {
        CategoryDto inputCategory = new CategoryDto(1L, "NewTestName");
        CategoryDto expectedCategory = inputCategory; // редактирование прошло успешно

        when(categoriesServiceMock.updateCategories(inputCategory))
                .thenReturn(new CategoryDto(inputCategory.getCategoryID(), inputCategory.getName())); //.thenReturn(expectedCategory)

        this.mockMvc.perform(put("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputCategory))) // jackson: object -> json
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.categoryID").exists())
                .andExpect(jsonPath("$.categoryID").value(expectedCategory.getCategoryID()))
                .andExpect(jsonPath("$.name").value(expectedCategory.getName()));
    }

    @Test
    void deleteCategories() throws Exception {

        Long inputId = 1L;

        this.mockMvc.perform(delete("/categories/{id}", inputId)) ///categories/1
                .andDo(print()) //печать лога вызова
                .andExpect(status().isOk());

        //return void
        verify(categoriesServiceMock).deleteCategories(inputId);

    }
//
//    @Test
//    void handleTwoException() {
//    }
//
//    @Test
//    void handleException() {
//    }
}