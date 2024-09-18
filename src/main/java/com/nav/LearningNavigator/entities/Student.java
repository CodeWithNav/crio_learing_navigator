package com.nav.LearningNavigator.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Student {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer regId;


    private String name;

}
