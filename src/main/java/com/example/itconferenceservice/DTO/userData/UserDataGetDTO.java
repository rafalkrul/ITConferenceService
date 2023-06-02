package com.example.itconferenceservice.DTO.userData;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDataGetDTO {

    private String login;

    private String email;
}
