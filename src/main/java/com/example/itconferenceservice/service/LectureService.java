package com.example.itconferenceservice.service;

import com.example.itconferenceservice.DTO.lecture.LectureGetDTO;
import com.example.itconferenceservice.DTO.lecture.ReservationPostDTO;
import com.example.itconferenceservice.DTO.userData.UserDataPostDTO;
import com.example.itconferenceservice.model.Conference;
import com.example.itconferenceservice.model.Lecture;
import com.example.itconferenceservice.model.UserData;
import com.example.itconferenceservice.repository.LectureRepository;
import com.example.itconferenceservice.repository.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final UserDataRepository userDataRepository;
    private final ModelMapper mapper;



    public void addUserToLecture(UserDataPostDTO userDataPostDTO, UUID id){
        Lecture lecture = mapper.map(lectureRepository.findById(id), Lecture.class);

        if(lecture.getUserDataList().size() > 5){
            throw new IllegalArgumentException("brak miejsc");
        }else{
            UserData userData = mapper.map(userDataPostDTO, UserData.class);
            lecture.getUserDataList().add(userData);
            lectureRepository.save(lecture);
        }
    }


    public List<Lecture> getLecturesByLogin(String login){
        return lectureRepository.findByUserDataList_Login(login)
                .stream()
                .map(lecture -> mapper.map(lecture,Lecture.class))
                .collect(Collectors.toList());

    }
    

}
