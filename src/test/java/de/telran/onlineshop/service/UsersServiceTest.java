package de.telran.onlineshop.service;

import de.telran.onlineshop.dto.UserDto;
import de.telran.onlineshop.entity.AddressEntity;
import de.telran.onlineshop.entity.CartEntity;
import de.telran.onlineshop.entity.FavoritesEntity;
import de.telran.onlineshop.entity.UsersEntity;
import de.telran.onlineshop.entity.enums.Role;
import de.telran.onlineshop.mapper.Mappers;
import de.telran.onlineshop.repository.CartRepository;
import de.telran.onlineshop.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {

    @Mock  //объект, поведение которого мы будем иммитировать
    private UsersRepository usersRepositoryMock;
    @Mock
    private CartRepository cartRepositoryMock;
    @Mock
    private Mappers mappersMock;

    @InjectMocks
    private UsersService usersServiceTest; // unit объект, который тестируется

    private UsersEntity userEntityTest1;
    private UserDto userDtoTest1;

    @BeforeEach
    void setUp() {
        userEntityTest1 = new UsersEntity(
                1L,
                "Вася Пупкин",
                "vasya@i.com",
                "+491601234567",
                "Password1",
                Role.CLIENT,
                new CartEntity(),
                new HashSet<FavoritesEntity>(),
                new HashSet<AddressEntity>()
        );

        userDtoTest1 = new UserDto(
                1L,
                "Вася Пупкин",
                "vasya@i.com",
                "+491601234567",
                "Password1"
        );
    }

    @Test
    void getAllUsersTest() {

        UsersEntity userEntityTest2 = new UsersEntity(
                2L,
                "Дуня Смирнова",
                "dunya@i.com",
                "+491607654321",
                "Password2",
                Role.ADMIN,
                new CartEntity(),
                new HashSet<FavoritesEntity>(),
                new HashSet<AddressEntity>()
        );


        UserDto userDtoTest2 = new UserDto(
                2L,
                "Дуня Смирнова",
                "dunya@i.com",
                "+491607654321",
                "Password2"
        );

        when(usersRepositoryMock.findAll()).thenReturn(List.of(userEntityTest1, userEntityTest2));
        when(mappersMock.convertToUserDto(userEntityTest1)).thenReturn(userDtoTest1);
        when(mappersMock.convertToUserDto(userEntityTest2)).thenReturn(userDtoTest2);

        List<UserDto> actualUserDtoList = usersServiceTest.getAllUsers();

        // проверка
        assertNotNull(actualUserDtoList);
        assertTrue(actualUserDtoList.size()>0);
        assertEquals(2, actualUserDtoList.size());
        assertEquals(1, actualUserDtoList.get(0).getUserID());
        assertEquals(userDtoTest1, actualUserDtoList.get(0));
        verify(usersRepositoryMock).findAll(); //был ли запущен этот метод
        verify(mappersMock, times(2)).convertToUserDto(any(UsersEntity.class)); //был ли запущен этот метод и сколько раз

    }

    @Test
    void getUserByIdTest() {
        Long testId = 1L;
        when(usersRepositoryMock.findById(testId)).thenReturn(Optional.of(userEntityTest1));
        when(mappersMock.convertToUserDto(userEntityTest1)).thenReturn(userDtoTest1);

        UserDto actualUserDto = usersServiceTest.getUserById(testId);

        // проверка
        assertNotNull(actualUserDto);
        assertEquals(testId, actualUserDto.getUserID());
        assertEquals(userDtoTest1, actualUserDto);
        verify(usersRepositoryMock).findById(testId); //был ли запущен этот метод
        verify(mappersMock, times(1)).convertToUserDto(any(UsersEntity.class)); //был ли запущен этот метод и сколько раз

    }

    @Test
    void updateUserTest() {

          when(mappersMock.convertToUserEntity(userDtoTest1)).thenReturn(userEntityTest1);
          when(usersRepositoryMock.save(userEntityTest1)).thenReturn(userEntityTest1);
          when(mappersMock.convertToUserDto(userEntityTest1)).thenReturn(userDtoTest1);


        UserDto actualUserDto = usersServiceTest.updateUser(userDtoTest1);

        // проверка
        assertNotNull(actualUserDto);
        assertEquals(userDtoTest1.getUserID(), actualUserDto.getUserID());
        assertEquals(userDtoTest1, actualUserDto);

        verify(mappersMock).convertToUserEntity(userDtoTest1); //был ли запущен этот метод
        verify(usersRepositoryMock).save(userEntityTest1); //был ли запущен этот метод
        verify(mappersMock).convertToUserDto(userEntityTest1); //был ли запущен этот метод

    }

    @Test
    void insertUsersTest() {
        UsersEntity userEntityTestInput = new UsersEntity(
                null,
                "Вася Пупкин",
                "vasya@i.com",
                "+491601234567",
                "Password1",
                Role.CLIENT,
                new CartEntity(),
                new HashSet<FavoritesEntity>(),
                new HashSet<AddressEntity>()
        );

        UserDto userDtoTestInput = new UserDto(
                null,
                "Вася Пупкин",
                "vasya@i.com",
                "+491601234567",
                "Password1"
        );

        when(mappersMock.convertToUserEntity(userDtoTestInput)).thenReturn(userEntityTestInput);
        when(usersRepositoryMock.save(userEntityTestInput)).thenReturn(userEntityTest1);
        when(mappersMock.convertToUserDto(userEntityTest1)).thenReturn(userDtoTest1);

        UserDto actualUserDto = usersServiceTest.insertUsers(userDtoTestInput); //запуск реального метода

        // проверка
        assertNotNull(actualUserDto);
        assertNotNull(actualUserDto.getUserID());
        assertNotEquals(userDtoTestInput.getUserID(), actualUserDto.getUserID());
        assertNotEquals(userDtoTestInput, actualUserDto);

        verify(mappersMock).convertToUserEntity(userDtoTestInput); //был ли запущен этот метод
        verify(usersRepositoryMock).save(userEntityTestInput); //был ли запущен этот метод
        verify(mappersMock).convertToUserDto(userEntityTest1); //был ли запущен этот метод
    }


    @Test
    void deleteUsersByIdTest() {
        Long testId = 1L;
        when(usersRepositoryMock.findById(testId)).thenReturn(Optional.of(userEntityTest1));

        usersServiceTest.deleteUsersById(testId); //запуск реального метода

        verify(usersRepositoryMock).delete(userEntityTest1); //был ли запущен этот метод
    }

    // Exception
    @Test
    void deleteUsersByIdNotFoundTest() {
        Long testId = 1L;
        when(usersRepositoryMock.findById(testId)).thenReturn(Optional.ofNullable(null));

        assertThrows(NullPointerException.class, () -> usersServiceTest.deleteUsersById(testId));
    }


    @Test
    void initTest() {
        when(cartRepositoryMock.save(any(CartEntity.class))).thenReturn(new CartEntity());
        when(usersRepositoryMock.save(any(UsersEntity.class))).thenReturn(new UsersEntity());
        usersServiceTest.init();
        verify(cartRepositoryMock,times(2)).save(any(CartEntity.class)); //был ли запущен этот метод
        verify(usersRepositoryMock,times(2)).save(any(UsersEntity.class)); //был ли запущен этот метод
    }
}