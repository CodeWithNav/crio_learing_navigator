package com.nav.LearningNavigator.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nav.LearningNavigator.constant.EndPoints;
import com.nav.LearningNavigator.dto.StudentRequestDto;
import com.nav.LearningNavigator.entities.Student;
import com.nav.LearningNavigator.services.IStudentService;
import com.nav.LearningNavigator.services.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {


    @Autowired
    MockMvc mockMvc;


    @MockBean
    IStudentService studentService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getStudentById() throws Exception{
        when(studentService.getById(1)).thenReturn(Student.builder().name("Nav").regId(1).build());

        mockMvc.perform(get(EndPoints.STUDENTS+"/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Nav"));
    }


    @Test
    void postStudentById() throws Exception{
        when(studentService.addStudent(StudentRequestDto.builder().name("Nav").build())).thenReturn(Student.builder().name("Nav").regId(1).build());

        StudentRequestDto studentRequestDto = StudentRequestDto.builder().name("Nav").build();
        String studentJson = objectMapper.writeValueAsString(studentRequestDto);

        mockMvc.perform(post(EndPoints.STUDENTS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJson))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Nav"));
    }


    @Test
    void updateStudentById() throws Exception{
        StudentRequestDto studentRequestDto =   StudentRequestDto.builder().name("Navpreet").build();
        when(studentService.updateStudentName(studentRequestDto,1)).thenReturn(Student.builder().name("Navpreet").regId(1).build());

        String studentJson = objectMapper.writeValueAsString(studentRequestDto);

        mockMvc.perform(put(EndPoints.STUDENTS+"/{id}",1)
                        .contentType(MediaType.APPLICATION_JSON)
                    .content(studentJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Navpreet"));
    }

}

