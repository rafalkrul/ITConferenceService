package com.example.itconferenceservice.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Conference")
public class Conference {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime starting_date;

    @Column(nullable = false)
    private LocalDateTime ending_date;

    @OneToMany
    private List<Track> tracksList;

}
