package com.example.itconferenceservice.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Reservation")
public class Reservation {

    @Id
    @GeneratedValue
    private UUID id;


    @Column(nullable = false)
    private UUID user_id;


    @Column(nullable = false)
    private UUID lecture_id;

}
