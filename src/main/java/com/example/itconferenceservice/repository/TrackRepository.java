package com.example.itconferenceservice.repository;

import com.example.itconferenceservice.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrackRepository extends JpaRepository<Track, UUID> {
}
