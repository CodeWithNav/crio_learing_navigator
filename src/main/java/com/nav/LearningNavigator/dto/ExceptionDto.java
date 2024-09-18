package com.nav.LearningNavigator.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionDto<T> {

    T error;

}
