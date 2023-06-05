package com.example.itconferenceservice.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Lecture")
public class Lecture {


    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalTime start_time;

    @Column(nullable = false)
    private LocalTime end_time;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserData> userDataList;

}
