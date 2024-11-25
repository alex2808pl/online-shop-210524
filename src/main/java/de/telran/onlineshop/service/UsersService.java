package de.telran.onlineshop.service;

import de.telran.onlineshop.dto.UserDto;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {

    private List<UserDto> userList;


    @PostConstruct
    void init() {
        userList = new ArrayList<>();
        userList.add(new UserDto(1L, "Вася Пупкин", "a@test.us", "1111111", "12345"));
        userList.add(new UserDto(2L, "Дуня Петрова", "duna@test.us", "2222222", "34567"));

        System.out.println("Выполняем логику при создании объекта "+this.getClass().getName());
    }
    public List<UserDto> getAllUsers() {
        return userList;
    }

    public UserDto getUserById(Long id) {
        return userList.stream()
                .filter(user -> user.getUserID()==id)
                .findFirst()
                .orElse(null);
    }

    public UserDto updateUser(UserDto user) {
        UserDto result = userList.stream()
                .filter(u -> u.getUserID() == user.getUserID())
                .findFirst()
                .orElse(null);
        if(result!=null) {
            result.setName(user.getName());
            result.setEmail(user.getEmail());
            result.setPhoneNumber(user.getPhoneNumber());
        }
        return result;
    }
}
