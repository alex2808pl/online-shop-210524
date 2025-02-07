package de.telran.onlineshop.service;

import de.telran.onlineshop.configure.MapperUtil;
import de.telran.onlineshop.dto.UserDto;
import de.telran.onlineshop.entity.CartEntity;
import de.telran.onlineshop.entity.UsersEntity;
import de.telran.onlineshop.entity.enums.Role;
import de.telran.onlineshop.mapper.Mappers;
import de.telran.onlineshop.repository.CartRepository;
import de.telran.onlineshop.repository.UsersRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final CartRepository cartRepository;
    private final Mappers mappers;
    private final PasswordEncoder passwordEncoder;

    List<UserDto> userList = new ArrayList<>();

    //@PostConstruct
    void init() {
        CartEntity cart1 = new CartEntity();
        cart1 = cartRepository.save(cart1);
        UsersEntity user1 = new UsersEntity(null, "Вася Пупкин", "a@test.us", "1111111", "12345",  Role.CLIENT, cart1, null);
        usersRepository.save(user1);

        CartEntity cart2 = new CartEntity();
        cart2 = cartRepository.save(cart2);
        UsersEntity user2 = new UsersEntity(null, "Дуня Семенова", "a@test.us", "1111111", "12345",  Role.ADMIN, cart2, null);
        usersRepository.save(user2);

        System.out.println("Выполняем логику при создании объекта "+this.getClass().getName());
    }

    public List<UserDto> getAllUsers() {
        List<UsersEntity> usersEntities = usersRepository.findAll();
        List<UserDto> userDtoList = MapperUtil.convertList(usersEntities, mappers::convertToUserDto);
        return userDtoList;
    }

    public UserDto getUserById(Long id) throws FileNotFoundException {
        if(id<0) {
            throw new FileNotFoundException(id+" - не найдено!");
        }
        UsersEntity usersEntity = usersRepository.findById(id).orElse(new UsersEntity());
        UserDto userDto = mappers.convertToUserDto(usersEntity);
        return userDto;
    }

    public UserDto updateUser(UserDto user) {
        UsersEntity usersEntity = mappers.convertToUserEntity(user); // из Dto в Entity

        UsersEntity returnUserEntity = usersRepository.save(usersEntity); // сохранили в БД

        return mappers.convertToUserDto(returnUserEntity); //из Entity  в Dto
    }

    public UserDto insertUsers(UserDto usersDto) {
        UsersEntity usersEntity = mappers.convertToUserEntity(usersDto);

        usersEntity.setUserId(null);
        //хэшируем пароль
        usersEntity.setPasswordHash(passwordEncoder.encode(usersEntity.getPasswordHash()));

        UsersEntity savedUsersEntity = usersRepository.save(usersEntity);

        return mappers.convertToUserDto(savedUsersEntity);
    }

    public void deleteUsersById(Long id) {
        UsersEntity usersEntity = usersRepository.findById(id).orElse(null);
        if (usersEntity != null) {
            usersRepository.delete(usersEntity);
        } else {
            throw new NullPointerException("Not Found UsersEntity");
        }
    }

    public UserDto getByEmail(String login) {
        UsersEntity usersEntity = usersRepository.getByEmail(login).stream().findFirst().orElse(null);
        usersEntity.setFavorites(null);
        return mappers.convertToUserDto(usersEntity);
    }
}
