package com.nav.LearningNavigator.services;

import com.nav.LearningNavigator.dto.SubjectRequestDto;
import com.nav.LearningNavigator.entities.Subject;
import com.nav.LearningNavigator.exceptions.NotFoundException;
import com.nav.LearningNavigator.repository.ISubjectRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class SubjectService implements ISubjectService{

    final ISubjectRepo subjectRepo;

    @Override
    public Subject addSubject(SubjectRequestDto subjectRequest) {
        Subject subject = Subject.builder().name(subjectRequest.getName()).build();

        return subjectRepo.save(subject);
    }

    @Override
    public Subject updateSubject(SubjectRequestDto subjectRequest, Integer id) {
        Subject subject = getSubjectById(id);
        subject.setName(subjectRequest.getName());
        return subjectRepo.save(subject);
    }

    @Override
    public void deleteSubject(Integer id) {
        getSubjectById(id);
        subjectRepo.deleteById(id);
    }

    @Override
    public List<Subject> listSubjects() {
        return subjectRepo.findAll();
    }

    @Override
    public Subject getSubjectById(Integer id) {
        return subjectRepo.findById(id).orElseThrow(()->new NotFoundException("Subject not found with id: " + id));
    }
}
