package com.example.itconferenceservice.DTO.track;

import com.example.itconferenceservice.model.Lecture;
import lombok.Data;


import java.util.List;
import java.util.UUID;

@Data
public class TrackPostDTO {


    private UUID id;

    private String name;

    private List<Lecture> lecturesList;
}
