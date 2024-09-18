package com.nav.LearningNavigator.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExamRequestDto {
    @NotBlank
    @Length(min = 3,max = 50)
    String name;

    @NotBlank
    Integer subjectId;



}
