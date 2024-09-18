package com.nav.LearningNavigator.controller;


import com.nav.LearningNavigator.constant.EndPoints;
import com.nav.LearningNavigator.dto.*;
import com.nav.LearningNavigator.entities.Student;
import com.nav.LearningNavigator.services.IStudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(EndPoints.STUDENTS)
@RequiredArgsConstructor
public class StudentController {


    final IStudentService service;


    @GetMapping
    public ResponseEntity<List<Student>> list(){
        return ResponseEntity.ok(service.getAllStudent());
    }


    @PostMapping
    public ResponseEntity<Student> create(@Valid @RequestBody StudentRequestDto studentRequestDto){
        final Student student = service.addStudent(studentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> update(@PathVariable(name = "id") Integer id,@Valid @RequestBody StudentRequestDto studentRequestDto){
        final Student student = service.updateStudentName(studentRequestDto,id);

        return  ResponseEntity.status(HttpStatus.OK).body(student);
    }


    @GetMapping("{id}")
    public ResponseEntity<Student> one(@PathVariable(name="id") Integer id){
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("{id}")
    public  ResponseEntity<?> delete(@PathVariable(name="id") Integer id){
        service.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }



    @PostMapping(EndPoints.STUDENT_ENROLL_SUBJECT+"/{id}")
    public ResponseEntity<StudentSubjectExamDto> enrollSubject(@PathVariable(name = "id") Integer id, @Valid @RequestBody EnrollSubjectDto enrollSubjectDto){

        return  ResponseEntity.ok(service.enrollSubject(enrollSubjectDto,id));
    }

    @PostMapping(EndPoints.STUDENT_REGISTER_EXAM +"/{id}")
    public ResponseEntity<StudentSubjectExamDto> registerExam(@PathVariable(name = "id") Integer id, @Valid @RequestBody RegisterExamDto registerExamDto){

        return  ResponseEntity.ok(service.registerExam(registerExamDto,id));
    }



    @PutMapping(EndPoints.STUDENT_UN_ENROLL_SUBJECT+"/{id}")
    public  ResponseEntity<?> unEnrollSubject(@PathVariable(name = "id") Integer id,@Valid @RequestBody EnrollSubjectDto enrollSubjectDto){
            service.unEnrollSubject(enrollSubjectDto,id);
            return ResponseEntity.noContent().build();
    }


    @PutMapping(EndPoints.STUDENT_UNREGISTER_EXAM+"/{id}")
    public  ResponseEntity<?> unregisterExam(@PathVariable(name = "id") Integer id,@Valid @RequestBody UnRegisterExamDto unRegisterExamDto){
        service.unRegisterExam(unRegisterExamDto,id);
        return ResponseEntity.noContent().build();
    }
    


}
