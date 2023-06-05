package com.example.itconferenceservice.controller;

import com.example.itconferenceservice.DTO.conference.ConferenceGetDTO;
import com.example.itconferenceservice.DTO.conference.ConferencePostDTO;
import com.example.itconferenceservice.service.ConferenceService;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ConferenceController {


    private final ConferenceService conferenceService;


    @PostMapping("/conference")
    public ResponseEntity<UUID> createConference(@RequestBody ConferencePostDTO conferencePostDTO){
        var conference = conferenceService.createConference(conferencePostDTO);
        return new ResponseEntity<>(conference, HttpStatus.CREATED);
    }

    @GetMapping("/conference/{id}")
    public ResponseEntity<ConferenceGetDTO> getConferenceById(@PathVariable UUID id){
        var conference = conferenceService.getConference(id);
        return new ResponseEntity<>(conference, HttpStatus.OK);
    }

    @PostMapping("/addTracks")
    public ResponseEntity<Void> addTracksToConference(@RequestBody ConferencePostDTO conferencePostDTO){
        conferenceService.addTracksToConference(conferencePostDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("lectureReport")
    public ResponseEntity<Map<String, Double>> generateLecturesReport(){
        Map<String,Double> report = conferenceService.generateLecturesReport();
        return new ResponseEntity<>(report,HttpStatus.OK);
    }

    @GetMapping("trackReport")
    public ResponseEntity<Map<String, Double>> generateTracksReport(){
        Map<String,Double> report = conferenceService.generateTracksReport();
        return new ResponseEntity<>(report,HttpStatus.OK);
    }


}
