package com.nav.LearningNavigator.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@Table(name = "subject")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Subject {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    Integer id;
    String name;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "subjects_students",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")

    )
    List<Student> students;

}
