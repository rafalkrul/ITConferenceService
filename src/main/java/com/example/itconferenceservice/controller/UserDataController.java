package com.example.itconferenceservice.controller;

import com.example.itconferenceservice.DTO.userData.UserDataGetDTO;
import com.example.itconferenceservice.DTO.userData.UserDataPostDTO;
import com.example.itconferenceservice.model.UserData;
import com.example.itconferenceservice.service.UserDataService;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserData>> getAllUsers(){
        List<UserData> userDataList = userDataService.getAllUsers();
        return new ResponseEntity<>(userDataList, HttpStatus.OK);
    }

    @PostMapping("changeEmail")
    public ResponseEntity<Void> changeEmail(@RequestBody UserDataGetDTO userDataGetDTO){
        userDataService.changeEmail(userDataGetDTO.getId(),userDataGetDTO.getEmail());
        return ResponseEntity.ok().build();
    }

}
