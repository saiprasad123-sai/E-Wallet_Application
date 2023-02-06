package com.projects.majorProjects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/com.projects.user")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserRequestDto userRequestDto){
        userService.addUser(userRequestDto);
        return new ResponseEntity<>("com.projects.user added successfully", HttpStatus.CREATED);
    }
    @GetMapping("/findByUser/{userName}")
    public ResponseEntity<User> getUserByUserName(@PathVariable("userName") String userName){
        userService.getUserByUserName(userName);
        return new ResponseEntity<>( HttpStatus.FOUND);
    }
}
