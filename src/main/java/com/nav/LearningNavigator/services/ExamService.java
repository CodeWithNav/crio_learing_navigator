package com.nav.LearningNavigator.services;

import com.nav.LearningNavigator.dto.ExamRequestDto;
import com.nav.LearningNavigator.entities.Exam;
import com.nav.LearningNavigator.entities.Subject;
import com.nav.LearningNavigator.exceptions.NotFoundException;
import com.nav.LearningNavigator.repository.IExamRepo;
import com.nav.LearningNavigator.repository.ISubjectRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ExamService implements  IExamService{
    final IExamRepo examRepo;
    final ISubjectRepo subjectRepo;
    @Override
    public Exam addExam(ExamRequestDto examRequest) {
        Subject subject = getSubjectId(examRequest.getSubjectId());
        Exam exam = Exam.builder().name(examRequest.getName()).subject(subject).build();
        return  examRepo.save(exam);
    }

    @Override
    public Exam updateExam(ExamRequestDto examRequest, Integer id) {
        Exam exam = getById(id);
        Subject subject = getSubjectId(examRequest.getSubjectId());

        exam.setName(examRequest.getName());

        exam.setSubject(subject);
        return examRepo.save(exam);
    }

    @Override
    public void deleteExam(Integer id) {
        getById(id);
        examRepo.deleteById(id);
    }

    @Override
    public List<Exam> listSubjectExams(Integer subjectId) {
        return examRepo.findAllBySubjectId(subjectId);
    }


    @Override
    public List<Exam> listExams() {
        return examRepo.findAll();
    }

    @Override
    public Exam getById(Integer id) {
        return examRepo.findById(id).orElseThrow(()->new NotFoundException("Exam not found with id: "+id));
    }


    private Subject getSubjectId(Integer id){
        return subjectRepo.findById(id).orElseThrow(()->new NotFoundException("Subject not found with id:"+id));
    }
}
