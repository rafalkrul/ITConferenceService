package com.example.itconferenceservice.DTO.conference;

import com.example.itconferenceservice.model.Track;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class ConferenceGetDTO {

    private String name;

    private LocalDateTime starting_date;

    private LocalDateTime ending_date;

    private List<Track> tracksList;
}
