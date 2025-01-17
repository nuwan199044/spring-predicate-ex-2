package com.myapp.predicate2.controller;

import com.myapp.predicate2.dto.StudentDTO;
import com.myapp.predicate2.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/{name}")
    public StudentDTO getStudentByName(@PathVariable(name = "name") String name) {
        return studentService.findStudentByName(name);
    }

    @GetMapping("/city/{city}")
    public List<StudentDTO> getStudentByCity(@PathVariable(name = "city") String city) {
        return studentService.findStudentByCity(city);
    }

    @GetMapping("/subject/{subjectName}")
    public List<StudentDTO> getStudentBySubject(@PathVariable(name = "subjectName") String subjectName) {
        return studentService.findStudentBySubject(subjectName);
    }

}
