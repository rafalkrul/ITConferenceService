package com.example.itconferenceservice.service;

import com.example.itconferenceservice.DTO.userData.UserDataPostDTO;
import com.example.itconferenceservice.model.UserData;
import com.example.itconferenceservice.repository.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDataService {


    private final UserDataRepository userDataRepository;

    private final ModelMapper mapper;




    public UUID createUser(UserDataPostDTO userDataPostDTO){

        if(userDataRepository.existsByLogin(userDataPostDTO.getLogin())){
            throw new IllegalArgumentException("Podany login jest już zajęty");
        }
        validateEmail(userDataPostDTO.getEmail());

        UserData user = mapper.map(userDataPostDTO, UserData.class);

        userDataRepository.save(user);

        return user.getId();
    }

    public void deleteUserById(UUID id){
        userDataRepository.deleteById(id);
    }


    public List<UserData> getAllUsers(){
        return userDataRepository.findAll()
                .stream()
                .map(userData -> mapper.map(userData, UserData.class))
                .collect(Collectors.toList());
    }


    public boolean validateEmail(String email){

        final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        if(userDataRepository.existsByEmail(email)){

            throw new IllegalArgumentException("Given email is taken");
        }
        return email.matches(EMAIL_PATTERN);
    }

    public void changeEmail(UUID id, String email){

        UserData userData = mapper.map(userDataRepository.findById(id), UserData.class);

        if(!email.isEmpty()){
            userData.setEmail(email);
        }
        userDataRepository.save(userData);
    }

}
