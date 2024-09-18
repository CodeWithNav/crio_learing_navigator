package com.nav.LearningNavigator.services;

import com.nav.LearningNavigator.dto.*;
import com.nav.LearningNavigator.entities.Exam;
import com.nav.LearningNavigator.entities.Student;
import com.nav.LearningNavigator.entities.Subject;
import com.nav.LearningNavigator.exceptions.AlreadyExistException;
import com.nav.LearningNavigator.exceptions.NotFoundException;
import com.nav.LearningNavigator.repository.IExamRepo;
import com.nav.LearningNavigator.repository.IStudentRepo;
import com.nav.LearningNavigator.repository.ISubjectRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {

    final IStudentRepo studentRepo;
    final ISubjectRepo subjectRepo;
    final IExamRepo examRepo;
    final ISubjectService subjectService;
    final IExamService examService;
    @Override
    public Student addStudent(StudentRequestDto studentRequest) {
        Student student = Student.builder().name(studentRequest.getName()).build();
        return studentRepo.save(student);
    }

    @Override
    public Student updateStudentName(StudentRequestDto studentRequest, Integer id) {
        Student student  = getById(id);
        student.setName(studentRequest.getName());
        return studentRepo.save(student);
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepo.findAll();
    }

    @Override
    public void deleteStudent(Integer id) {
        getById(id);

        studentRepo.deleteById(id);
    }

    @Override
    public Student getById(Integer id) {
        return studentRepo.findById(id).orElseThrow(()->new NotFoundException("Student not found with id: " +id));
    }

    @Override
    public StudentSubjectExamDto enrollSubject(EnrollSubjectDto enrollSubjectDto, Integer id) {

        Student student = getById(id);
        Subject subject = subjectService.getSubjectById(enrollSubjectDto.getSubjectId());

        boolean alreadyNotPresent  = subject.getStudents().stream().noneMatch((student1 ->
            Objects.equals(student1.getRegId(), id)
        ));
        if(!alreadyNotPresent) throw new AlreadyExistException("Student already enrolled in this subject");
        List<Student> students = subject.getStudents();
        students.add(student);
        subject.setStudents(students);
        subjectRepo.save(subject);
        List<Subject> subjects =  subjectRepo.findAllByStudents(student);
        return  StudentSubjectExamDto.builder().student(student).subject(subjects).build();
    }

    @Override
    public StudentSubjectExamDto registerExam(RegisterExamDto registerExamDto, Integer id) {
        Student student = getById(id);



        Exam exam = examService.getById(registerExamDto.getExamId());

        boolean haveSubject = exam.getSubject().getId().equals(registerExamDto.getSubjectId());

        if(!haveSubject){
            throw new NotFoundException("Exam or Subject not found");
        }

        boolean studentEnrolledSubject =  exam.getSubject().getStudents().stream().anyMatch(student1 ->
                Objects.equals(student1.getRegId(), id)
        );
        if(!studentEnrolledSubject){
            throw new NotFoundException("Student not enrolled in this subject");
        }


        exam.getStudents().add(student);
        examRepo.save(exam);
        Subject subject = exam.getSubject();
        return StudentSubjectExamDto.builder().student(student).subject(List.of(subject)).exams(List.of(exam)).build();
    }

    @Override
    public void unRegisterExam(UnRegisterExamDto unRegisterExamDto, Integer id) {
        Exam exam = examService.getById(unRegisterExamDto.getExamId());
        exam.getStudents().removeIf(student -> student.getRegId().equals(id));
        examRepo.save(exam);
    }

    @Override
    public void unEnrollSubject(EnrollSubjectDto enrollSubjectDto, Integer id) {
        getById(id);
        Subject subject = subjectService.getSubjectById(enrollSubjectDto.getSubjectId());
        subject.getStudents().removeIf(student1 -> student1.getRegId().equals(id));
        examService.listSubjectExams(subject.getId()).forEach((exam -> unRegisterExam(new UnRegisterExamDto(exam.getId()),id)));
        subjectRepo.save(subject);
    }
}
