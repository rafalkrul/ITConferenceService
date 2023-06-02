package com.example.itconferenceservice.service;

import com.example.itconferenceservice.DTO.userData.UserDataPostDTO;
import com.example.itconferenceservice.model.UserData;
import com.example.itconferenceservice.repository.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDataService {


    private final UserDataRepository userDataRepository;

    private final ModelMapper mapper;




    public UUID createUser(UserDataPostDTO userDataPostDTO){

        UserData user = mapper.map(userDataPostDTO, UserData.class);

        userDataRepository.save(user);

        return user.getId();
    }








}
