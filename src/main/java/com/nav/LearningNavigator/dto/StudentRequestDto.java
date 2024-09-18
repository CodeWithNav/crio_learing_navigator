package com.nav.LearningNavigator.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDto {

    @NotBlank
    @Length(min = 3,max = 50)
    String name;

}
