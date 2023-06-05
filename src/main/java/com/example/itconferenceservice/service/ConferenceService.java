package com.example.itconferenceservice.service;

import com.example.itconferenceservice.DTO.conference.ConferenceGetDTO;
import com.example.itconferenceservice.DTO.conference.ConferencePostDTO;
import com.example.itconferenceservice.model.Conference;
import com.example.itconferenceservice.model.Lecture;
import com.example.itconferenceservice.model.Track;
import com.example.itconferenceservice.repository.ConferenceRepository;
import com.example.itconferenceservice.repository.LectureRepository;
import com.example.itconferenceservice.repository.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
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


    public Map<String, Double> generateLecturesReport(){

        List<Lecture> lectureList = lectureRepository.findAll();

        Map<String, Double> report = new HashMap<>();

        for(Lecture lecture : lectureList){

                double percentage = (double) lecture.getUserDataList().size() / 5;

                report.put(lecture.getTitle(),percentage * 100);
        }

        return report;
    }

    public Map<String, Double> generateTracksReport() {

        List<Track> trackList = trackRepository.findAll();

        Map<String, Double> report = new HashMap<>();

        double percentage = 0;

        for (Track track : trackList) {

                List<Lecture> lectureList = track.getLecturesList();

                for (Lecture lecture : lectureList) {

                         percentage += (double) lecture.getUserDataList().size() / 5;

                }


                report.put(track.getName(), percentage/track.getLecturesList().size() * 100);
                percentage = 0;

        }
        return report;
    }
}
