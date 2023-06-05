package com.example.itconferenceservice.repository;

import com.example.itconferenceservice.model.Lecture;
import com.example.itconferenceservice.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LectureRepository extends JpaRepository<Lecture, UUID> {


    List<Lecture> findByUserDataList_Login(String login);


}
