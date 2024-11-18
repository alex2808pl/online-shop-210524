package de.telran.onlineshop.service;

import de.telran.onlineshop.model.Category;
import de.telran.onlineshop.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {

    private List<User> userList;


    @PostConstruct
    void init() {
        userList = new ArrayList<>();
        userList.add(new User(1L, "Вася Пупкин", "a@test.us", "1111111", "12345"));
        userList.add(new User(2L, "Дуня Петрова", "duna@test.us", "2222222", "34567"));

        System.out.println("Выполняем логику при создании объекта "+this.getClass().getName());
    }
    public List<User> getAllUsers() {
        return userList;
    }

    public User getUserById(Long id) {
        return userList.stream()
                .filter(user -> user.getUserID()==id)
                .findFirst()
                .orElse(null);
    }

    public User updateUser(User user) {
        User result = userList.stream()
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
