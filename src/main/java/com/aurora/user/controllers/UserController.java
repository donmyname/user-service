package com.aurora.user.controllers;


import com.aurora.user.dtos.UserDetailsDTO;
import com.aurora.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/saveUpdate")
    public UserDetailsDTO saveUpdate(@RequestBody UserDetailsDTO inputUser){
        return userService.saveUpdate(inputUser);
    }

    @GetMapping(value ="/getById/{id}")
    public UserDetailsDTO getUserById(@PathVariable("id") Long id) { return  userService.getById((id));
    }

    @GetMapping(value = "/getByName/{name}")
    public List<UserDetailsDTO> getUserByName(@PathVariable String name) {
        return userService.getByName(name);
    }

    @GetMapping(value = "/getAll")
    public List<UserDetailsDTO> getAll(){
        return userService.getAll();
    }
}
