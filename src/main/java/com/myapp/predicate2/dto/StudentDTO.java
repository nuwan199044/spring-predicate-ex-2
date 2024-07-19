package com.myapp.predicate2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO implements Serializable {

    private Long id;
    private String name;
    private AddressDTO address;
    private Set<SubjectDTO> subjects;
}
