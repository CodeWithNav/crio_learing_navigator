package com.nav.LearningNavigator.controller;


import com.nav.LearningNavigator.constant.EndPoints;
import com.nav.LearningNavigator.dto.SubjectRequestDto;
import com.nav.LearningNavigator.entities.Subject;
import com.nav.LearningNavigator.services.ISubjectService;
import com.nav.LearningNavigator.services.ISubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(EndPoints.SUBJECTS)
@RequiredArgsConstructor
public class SubjectController {


    final ISubjectService service;


    @GetMapping
    public ResponseEntity<List<Subject>> list(){
        return ResponseEntity.ok(service.listSubjects());
    }


    @PostMapping
    public ResponseEntity<Subject> create(@RequestBody SubjectRequestDto subjectRequestDto){
        final Subject subject = service.addSubject(subjectRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(subject);
    }

    @PutMapping("{id}")
    public ResponseEntity<Subject> update(@PathVariable(name = "id") Integer id,@RequestBody SubjectRequestDto subjectRequestDto){
        final Subject subject = service.updateSubject(subjectRequestDto,id);

        return  ResponseEntity.status(HttpStatus.OK).body(subject);
    }
    
    
    @GetMapping("{id}")
    public ResponseEntity<Subject> one(@PathVariable(name="id") Integer id){
        return ResponseEntity.ok(service.getSubjectById(id));
    }
    
    @DeleteMapping("{id}")
    public  ResponseEntity<?> delete(@PathVariable(name="id") Integer id) {
        service.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }
}
