package com.nav.LearningNavigator.controller;


import com.nav.LearningNavigator.constant.EndPoints;
import com.nav.LearningNavigator.dto.ExamRequestDto;
import com.nav.LearningNavigator.dto.ExamRequestDto;
import com.nav.LearningNavigator.entities.Exam;
import com.nav.LearningNavigator.entities.Exam;
import com.nav.LearningNavigator.services.IExamService;
import com.nav.LearningNavigator.services.IExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(EndPoints.EXAMS)
@RequiredArgsConstructor
public class ExamController {


    final IExamService service;


    @GetMapping
    public ResponseEntity<List<Exam>> list(){
        return ResponseEntity.ok(service.listExams());
    }


    @PostMapping
    public ResponseEntity<Exam> create(@RequestBody ExamRequestDto examRequestDto){
        final Exam exam = service.addExam(examRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(exam);
    }

    @PutMapping("{id}")
    public ResponseEntity<Exam> update(@PathVariable(name = "id") Integer id,@RequestBody ExamRequestDto examRequestDto){
        final Exam exam = service.updateExam(examRequestDto,id);

        return  ResponseEntity.status(HttpStatus.OK).body(exam);
    }


    @GetMapping("{id}")
    public ResponseEntity<Exam> one(@PathVariable(name="id") Integer id){
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("{id}")
    public  ResponseEntity<?> delete(@PathVariable(name="id") Integer id){
        service.deleteExam(id);
        return ResponseEntity.noContent().build();
    }
}
