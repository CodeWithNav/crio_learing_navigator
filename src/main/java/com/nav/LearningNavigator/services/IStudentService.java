package com.nav.LearningNavigator.services;

import com.nav.LearningNavigator.dto.*;
import com.nav.LearningNavigator.entities.Student;

import java.util.List;

public interface IStudentService {

    public Student addStudent(StudentRequestDto studentRequest);
    public Student updateStudentName(StudentRequestDto studentRequest,Integer regId);
    public List<Student> getAllStudent();
    public void deleteStudent(Integer id);
    public Student getById(Integer id);

    public StudentSubjectExamDto enrollSubject(EnrollSubjectDto enrollSubjectDto,Integer id);
    public StudentSubjectExamDto registerExam(RegisterExamDto registerExamDto,Integer id);
    public void unRegisterExam(UnRegisterExamDto unRegisterExamDto, Integer id);


    public void unEnrollSubject(EnrollSubjectDto enrollSubjectDto,Integer id);

}
