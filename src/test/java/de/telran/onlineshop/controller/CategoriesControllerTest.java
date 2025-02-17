package de.telran.onlineshop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.onlineshop.dto.CategoryDto;
import de.telran.onlineshop.security.configure.SecurityConfig;
import de.telran.onlineshop.security.jwt.JwtAuthentication;
import de.telran.onlineshop.security.jwt.JwtProvider;
import de.telran.onlineshop.service.CategoriesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CategoriesController.class)
@Import(SecurityConfig.class)
@WithMockUser(username = "Test User", roles = {"ADMIN"})
class CategoriesControllerTest {

    @Autowired
    private MockMvc mockMvc; // для иммитации запросов пользователей

    @MockBean
    private CategoriesService categoriesServiceMock;

    @Autowired
    private ObjectMapper objectMapper;


    @MockBean
    private JwtProvider jwtProvider;

//    @BeforeEach
//    public void setup(WebApplicationContext webApplicationContext) {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//                .apply(springSecurity())
//                .build();
//    }

    @Test
    @WithMockUser(username = "Test User", roles = {"CLIENT","ADMIN"})
    void getAllCategories() throws Exception {
        when(categoriesServiceMock.getAllCategories()).thenReturn(List.of(new CategoryDto(1L,"Test")));
//        JwtAuthentication jwtAuthentication = new JwtAuthentication("arneoswald@example.com", List.of("CLIENT"));
//        jwtAuthentication.setAuthenticated(true);
        this.mockMvc.perform(get("/categories"))
//                        .with(authentication(jwtAuthentication)))
                .andDo(print()) //печать лога вызова
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..categoryID").exists())
                .andExpect(jsonPath("$..categoryID").value(1))
                .andExpect(jsonPath("$..name").value("Test"))
        ;

    }

    @WithMockUser(username = "Test User", roles = {"CLIENT"})
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
    @WithMockUser(username = "Test User", roles = {"CLIENT","ADMIN"})
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

    @WithMockUser(username = "Test User", roles = {"ADMIN"})
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