package com.nav.LearningNavigator.services;

import com.nav.LearningNavigator.dto.ExamRequestDto;
import com.nav.LearningNavigator.entities.Exam;

import java.util.List;

public interface IExamService {

    public Exam addExam(ExamRequestDto examRequest);
    public  Exam updateExam(ExamRequestDto examRequest,Integer id);
    public void  deleteExam(Integer id);
    public List<Exam> listSubjectExams(Integer subjectId);
    public List<Exam> listExams();
    public Exam getById(Integer id);
}
