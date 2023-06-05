package com.example.itconferenceservice.repository;

import com.example.itconferenceservice.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserDataRepository extends JpaRepository<UserData, UUID> {

    boolean existsByLogin(String login);

    boolean existsByEmail(String email);
}
