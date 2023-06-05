package com.example.itconferenceservice.DTO.lecture;

import com.example.itconferenceservice.model.UserData;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
@Data
public class LectureGetDTO {
    private UUID id;
    private String title;
    private LocalTime start_time;
    private LocalTime end_time;
    private List<UserData> userDataList;

}
