package de.telran.onlineshop.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.onlineshop.dto.CategoryDto;
import de.telran.onlineshop.dto.UserDto;
import de.telran.onlineshop.entity.AddressEntity;
import de.telran.onlineshop.entity.CartEntity;
import de.telran.onlineshop.entity.FavoritesEntity;
import de.telran.onlineshop.entity.UsersEntity;
import de.telran.onlineshop.entity.enums.Role;
import de.telran.onlineshop.repository.CategoriesRepository;
import de.telran.onlineshop.repository.UsersRepository;
import de.telran.onlineshop.service.CategoriesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest //запускаем контейнер Spring
@AutoConfigureMockMvc(printOnlyOnFailure = false)
@ActiveProfiles(profiles = { "dev" })
public class UsersIntegrationTest {
    @Autowired
    private MockMvc mockMvc; // для иммитации запросов пользователей
    @MockBean
    private UsersRepository usersRepositoryMock;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getPartSystemAllUsersTest() throws Exception { // посылаем запрос в БД
              this.mockMvc.perform(get("/users"))
                .andDo(print()) //печать лога вызова
                .andExpect(status().isOk());
    }

    @Test
    void getIntegrationAllUsersTest() throws Exception { // имитрируем запрос в БД
        when(usersRepositoryMock.findAll()).thenReturn(List.of(
                new UsersEntity(
                        1L,
                        "Вася Пупкин",
                        "vasya@i.com",
                        "+491601234567",
                        "Password1",
                        Role.CLIENT,
                        new CartEntity(),
                        new HashSet<FavoritesEntity>()
                ))
        );

        this.mockMvc.perform(get("/users"))
                .andDo(print()) //печать лога вызова
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..userID").exists())
                .andExpect(jsonPath("$..name").exists());
    }

    @Test
    void createPartSystemUsersTest() throws Exception {

        UserDto usersDtoInput = new UserDto(
                null,
                "Вася Пупкин",
                "vasya@i.com",
                "+491601234567",
                "Password1",
                Role.ADMIN
        );

        this.mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usersDtoInput))) // jackson: object -> json
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void createIntegrationUsersTest() throws Exception {

        UserDto usersDtoInput = new UserDto(
                null,
                "Вася Пупкин",
                "vasya@i.com",
                "+491601234567",
                "Password1",
                Role.ADMIN
        );

        when(usersRepositoryMock.save(any(UsersEntity.class))).thenReturn(
                new UsersEntity(
                        1L,
                        "Вася Пупкин",
                        "vasya@i.com",
                        "+491601234567",
                        "Password1",
                        Role.CLIENT,
                        new CartEntity(),
                        new HashSet<FavoritesEntity>()
                ));

        this.mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usersDtoInput))) // jackson: object -> json
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userID").exists())
                .andExpect(jsonPath("$.userID").value(1));
    }
}
