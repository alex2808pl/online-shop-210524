package de.telran.onlineshop.controller;

import de.telran.onlineshop.model.Category;
import de.telran.onlineshop.model.User;
import de.telran.onlineshop.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping  //select
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = usersService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.valueOf(200));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = usersService.getUserById(id);
        return ResponseEntity.status(222).body(user);
    }

    @PutMapping
    public ResponseEntity<User> updateClient(@RequestBody User user)  {
        User userResponse = usersService.updateUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }




}
