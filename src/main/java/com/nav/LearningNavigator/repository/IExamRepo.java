package com.nav.LearningNavigator.repository;

import com.nav.LearningNavigator.entities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IExamRepo extends JpaRepository<Exam,Integer> {


    List<Exam> findAllBySubjectId(Integer id);

}
