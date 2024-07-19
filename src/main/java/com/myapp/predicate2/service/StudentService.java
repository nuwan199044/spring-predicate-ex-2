package com.myapp.predicate2.service;

import com.myapp.predicate2.dto.StudentDTO;
import com.myapp.predicate2.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public StudentDTO findStudentByName(String name) {
        return modelMapper.map(studentRepository.findByName(name), StudentDTO.class);
    }

    public List<StudentDTO> findStudentByCity(String city) {
        return studentRepository.findByAddressCity(city)
                .stream()
                .map( student -> modelMapper.map(student, StudentDTO.class))
                .toList();
    }

    public List<StudentDTO> findStudentBySubject(String subjectName) {
        return studentRepository.findByAssignedSubjectsName(subjectName)
                .stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .toList();
    }
}
