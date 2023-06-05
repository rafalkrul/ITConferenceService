package com.example.itconferenceservice.service;

import com.example.itconferenceservice.DTO.userData.UserDataPostDTO;
import com.example.itconferenceservice.model.Lecture;
import com.example.itconferenceservice.model.UserData;
import com.example.itconferenceservice.repository.LectureRepository;
import com.example.itconferenceservice.repository.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final UserDataRepository userDataRepository;
    private final ModelMapper mapper;



    public void addUserToLecture(UUID user_id, UUID lecture_id){

        Lecture lecture = mapper.map(lectureRepository.findById(lecture_id), Lecture.class);

        UserData userData = mapper.map(userDataRepository.findById(user_id), UserData.class);

        List<Lecture> lectureList = getLecturesByLogin(userData.getLogin())
                .stream()
                .filter(l -> l.getStart_time().equals(lecture.getStart_time()))
                .collect(Collectors.toList());

        if(!lectureList.isEmpty()) {
            throw new IllegalArgumentException("Jesteś już zapisany na prelekcje o tej godzinie");
        }

        if (lecture.getUserDataList().size() >= 5) {
            throw new IllegalArgumentException("brak miejsc");
        }

        lecture.getUserDataList().add(userData);
        sendEmail(userData.getLogin(), userData.getEmail());
        lectureRepository.save(lecture);
    }


    public List<Lecture> getLecturesByLogin(String login){
        return lectureRepository.findByUserDataList_Login(login)
                .stream()
                .map(lecture -> mapper.map(lecture,Lecture.class))
                .collect(Collectors.toList());

    }


    public void deleteUserDataFromLecture(UUID user_id, UUID lecture_id){
        Lecture lecture = mapper.map(lectureRepository.findById(lecture_id), Lecture.class);

        lecture.setUserDataList(
                lecture.getUserDataList()
                        .stream()
                        .filter(userData -> !userData.getId().equals(user_id))
                        .collect(Collectors.toList())
        );
        lectureRepository.save(lecture);
    }

    public void sendEmail(String login, String email){
        String filePath = "EmailsSent.txt"; // Ścieżka do pliku

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(LocalTime.now() + " " + email + " " + "treść emailu"); // Zapisz słowa do pliku
            writer.newLine(); // Nowa linia

            System.out.println("Słowa zostały zapisane do pliku.");
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas zapisywania do pliku: " + e.getMessage());
        }
    }


}
