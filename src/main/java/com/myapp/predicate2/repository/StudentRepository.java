package com.myapp.predicate2.repository;

import com.myapp.predicate2.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByName(String name);

    List<Student> findByAddressCity(String city);

    List<Student> findByAssignedSubjectsName(String subjectName);
}
