package com.nav.LearningNavigator.services;

import com.nav.LearningNavigator.dto.SubjectRequestDto;
import com.nav.LearningNavigator.entities.Subject;

import java.util.List;

public interface ISubjectService {


    public Subject addSubject(SubjectRequestDto subjectRequest);
    public Subject updateSubject(SubjectRequestDto subjectRequest,Integer id);
    public void deleteSubject(Integer id);

    public List<Subject> listSubjects();


    public Subject getSubjectById(Integer id);

}
