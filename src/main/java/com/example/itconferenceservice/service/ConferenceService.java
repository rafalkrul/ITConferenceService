package com.example.itconferenceservice.service;

import com.example.itconferenceservice.DTO.conference.ConferenceGetDTO;
import com.example.itconferenceservice.DTO.conference.ConferencePostDTO;
import com.example.itconferenceservice.DTO.track.TrackPostDTO;
import com.example.itconferenceservice.model.Conference;
import com.example.itconferenceservice.model.Lecture;
import com.example.itconferenceservice.model.Track;
import com.example.itconferenceservice.repository.ConferenceRepository;
import com.example.itconferenceservice.repository.LectureRepository;
import com.example.itconferenceservice.repository.TrackRepository;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConferenceService {


    private final ConferenceRepository conferenceRepository;
    private final TrackRepository trackRepository;
    private final LectureRepository lectureRepository;

    private final ModelMapper mapper;



    public UUID createConference(ConferencePostDTO conferencePostDTO){

        Conference conference = mapper.map(conferencePostDTO, Conference.class);

//        var tracks =conference.getTracksList()
//                .stream()
//                .map(track -> mapper.map(track, Track.class))
//                .collect(Collectors.toList());

//        conference.setTracksList(tracks);

        conference.setTracksList(new ArrayList<>());
        conferenceRepository.save(conference);

        return conference.getId();

    }

    public ConferenceGetDTO getConference(UUID id){

        Optional<Conference> conferenceGetDTO = conferenceRepository.findById(id);

        return mapper.map(conferenceGetDTO, ConferenceGetDTO.class);

    }

    public void addTracksToConference(ConferencePostDTO conferencePostDTO){

        Conference conference = mapper.map(conferenceRepository.findById(conferencePostDTO.getId()), Conference.class);

        List<Track> trackList = conferencePostDTO.getTracksList()
                .stream()
                .map(track -> mapper.map(track, Track.class))
                .collect(Collectors.toList());

        List<Lecture> lectureList = trackList.stream()
                .flatMap(track -> track.getLecturesList().stream())
                .map(lectureDTO -> mapper.map(lectureDTO, Lecture.class))
                .collect(Collectors.toList());

        lectureRepository.saveAll(lectureList);

        trackRepository.saveAll(trackList);

        conference.getTracksList().addAll(trackList);
        conferenceRepository.save(conference);

    }






}
