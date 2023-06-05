package com.example.itconferenceservice.controller;

import com.example.itconferenceservice.DTO.lecture.UserLectureDTO;
import com.example.itconferenceservice.DTO.userData.UserDataPostDTO;
import com.example.itconferenceservice.model.Lecture;
import com.example.itconferenceservice.service.LectureService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LectureController {

    private final LectureService lectureService;

    @PostMapping("/addUserToLecture")
    public ResponseEntity<Void> addUserToLecture(@RequestBody UserLectureDTO userLectureDTO){

        lectureService.addUserToLecture(userLectureDTO.getUser_id(),userLectureDTO.getLecture_id());
        return ResponseEntity.ok().build();

    }

    @GetMapping("/UserLectures")
    public ResponseEntity<List<Lecture>> getLecturesByLogin(@RequestParam String login){
        List<Lecture> lectureList = lectureService.getLecturesByLogin(login);
        return new ResponseEntity<>(lectureList, HttpStatus.OK);
    }

    @PostMapping("deleteUserFromLecture")
    public ResponseEntity<Void> deleteUserFromLecture(@RequestBody UserLectureDTO userLectureDTO){
        lectureService.deleteUserDataFromLecture(userLectureDTO.getUser_id(),userLectureDTO.getLecture_id());
        return ResponseEntity.ok().build();
    }



}
