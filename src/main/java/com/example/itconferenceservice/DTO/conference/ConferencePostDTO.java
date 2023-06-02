package com.example.itconferenceservice.DTO.conference;

import com.example.itconferenceservice.model.Track;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class ConferencePostDTO {

    private UUID id;

    private String name;

    private LocalDateTime starting_date;

    private LocalDateTime ending_date;

    private List<Track> tracksList;

}
