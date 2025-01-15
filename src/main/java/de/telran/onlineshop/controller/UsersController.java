package de.telran.onlineshop.controller;

import de.telran.onlineshop.dto.UserDto;
import de.telran.onlineshop.aspect.LogAnnotation;
import de.telran.onlineshop.service.UsersService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
@Hidden
@Tag(name = "Секретный контролер User", description = "Позволяет работать с пользователями")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping  //select
    @LogAnnotation
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = usersService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.valueOf(200));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) throws FileNotFoundException {
        UserDto user = usersService.getUserById(id);
        return ResponseEntity.status(222).body(user);
    }

    @PutMapping
    public ResponseEntity<UserDto> updateClient(@RequestBody UserDto user)  {
        UserDto userResponse = usersService.updateUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PostMapping
    public ResponseEntity<UserDto> createClient(@RequestBody UserDto user)  {
        UserDto userResponse = usersService.insertUsers(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }




}
