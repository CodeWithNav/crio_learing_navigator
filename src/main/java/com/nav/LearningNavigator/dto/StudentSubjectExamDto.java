package com.nav.LearningNavigator.dto;


import com.nav.LearningNavigator.entities.Exam;
import com.nav.LearningNavigator.entities.Student;
import com.nav.LearningNavigator.entities.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentSubjectExamDto {

    private Student student;
    private List<Subject> subject;
    private List<Exam> exams;
}
