package com.nav.LearningNavigator.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;


@Getter
@Setter
@Builder
@Table(name = "exam")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Exam {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;
    private String name;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "exams_students",
            joinColumns = @JoinColumn(name = "exam_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Collection<Student> students;
}
