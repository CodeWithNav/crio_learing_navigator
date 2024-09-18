package com.nav.LearningNavigator.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("random")
@RequiredArgsConstructor
public class EasterEgg {

    final RestTemplate restTemplate;

    @GetMapping("{number}")
    public ResponseEntity<String> random(@PathVariable(name = "number") Integer number){
        return  restTemplate.getForEntity("http://numbersapi.com/"+number+"/math",String.class);
    }
}
