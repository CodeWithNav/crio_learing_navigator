package com.nav.LearningNavigator.repository;

import com.nav.LearningNavigator.entities.Student;
import com.nav.LearningNavigator.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISubjectRepo extends JpaRepository<Subject,Integer> {


    List<Subject> findAllByStudents(Student student);

}
