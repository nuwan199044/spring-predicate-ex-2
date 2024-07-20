package com.myapp.predicate2.dto;

import com.myapp.predicate2.enums.GlobalOperator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {
    private List<SearchFilter> filters;
    private GlobalOperator globalOperator;
    private PageRequestDto page;
}
