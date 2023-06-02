package com.example.itconferenceservice.controller;

import com.example.itconferenceservice.DTO.userData.UserDataPostDTO;
import com.example.itconferenceservice.service.UserDataService;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserDataController {


    private final UserDataService userDataService;



    @PostMapping("/user")
    public ResponseEntity<UUID> createUser(@RequestBody UserDataPostDTO userDataPostDTO){
        var user = userDataService.createUser(userDataPostDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }




}
