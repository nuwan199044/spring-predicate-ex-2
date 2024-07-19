package com.myapp.predicate2.service;

import com.myapp.predicate2.dto.StudentDTO;
import com.myapp.predicate2.entity.Student;
import com.myapp.predicate2.repository.StudentRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public List<StudentDTO> findStudentByName(String name) {
        Specification<Student> specification = new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("name"), name);
            }
        };
        return studentRepository.findAll(specification).stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .toList();
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
