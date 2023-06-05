package com.example.itconferenceservice.DTO.lecture;

import lombok.Data;
import java.util.UUID;

@Data
public class UserLectureDTO {

    private UUID user_id;
    private UUID lecture_id;
}
