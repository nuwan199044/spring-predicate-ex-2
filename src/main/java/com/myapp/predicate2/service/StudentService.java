package com.myapp.predicate2.service;

import com.myapp.predicate2.dto.PageRequestDto;
import com.myapp.predicate2.dto.SearchFilter;
import com.myapp.predicate2.dto.SearchRequest;
import com.myapp.predicate2.dto.StudentDTO;
import com.myapp.predicate2.entity.Student;
import com.myapp.predicate2.repository.StudentRepository;
import com.myapp.predicate2.specification.FilterSpecification;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final FilterSpecification<Student> filterSpecification;
    private final ModelMapper modelMapper;

    public StudentDTO findStudentByName(String name) {
        return modelMapper.map(studentRepository.findByName(name), StudentDTO.class);
    }

    public List<StudentDTO> findStudentByFilter(SearchFilter searchFilter) {
        Specification<Student> specification = filterSpecification.getSearchSpecification(searchFilter);
        return studentRepository.findAll(specification).stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .toList();
    }

    public List<StudentDTO> findStudentByFilter(List<SearchFilter> searchFilters) {
        Specification<Student> specification = filterSpecification.getSearchSpecification(searchFilters);
        List<Student> students = studentRepository.findAll(specification);
        return students.stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .toList();
    }

    public List<StudentDTO> findStudentByFilter(SearchRequest searchRequest) {
        Specification<Student> specification = filterSpecification.getSearchSpecification(searchRequest);
        List<Student> students = studentRepository.findAll(specification);
        return students.stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .toList();
    }

    public List<StudentDTO> findStudentByFilterOperation(SearchRequest searchRequest) {
        Specification<Student> specification = filterSpecification.getSearchSpecificationWithEqAndLike(searchRequest);
        List<Student> students = studentRepository.findAll(specification);
        return students.stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .toList();
    }

    public Page<StudentDTO> findStudentByFilterOperationAndPagination(SearchRequest searchRequest) {
        Specification<Student> specification = filterSpecification.getSearchSpecificationWithEqAndLike(searchRequest);
        Pageable page = new PageRequestDto().getPageable(searchRequest.getPage());
        Page<Student> studentPage = studentRepository.findAll(specification, page);
        return studentPage.map(student -> modelMapper.map(student, StudentDTO.class));

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
