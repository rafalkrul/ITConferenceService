package com.example.itconferenceservice.repository;

import com.example.itconferenceservice.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LectureRepository extends JpaRepository<Lecture, UUID> {
}
