package com.myapp.predicate2.dto;

import com.myapp.predicate2.enums.Operation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchFilter {
    private String column;
    private String value;
    private Operation operation;
}
