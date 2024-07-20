package com.myapp.predicate2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PageRequestDto {

    private Integer pageNo = 0;
    private Integer pageSize = 10;
    private Sort.Direction sort = Sort.Direction.ASC;
    private String sortColumn = "id";

    public Pageable getPageable(PageRequestDto pageRequestDto) {
        Integer pageNo = Objects.nonNull(pageRequestDto.getPageNo()) ? pageRequestDto.getPageNo() : this.pageNo;
        Integer pageSize = Objects.nonNull(pageRequestDto.getPageSize()) ? pageRequestDto.getPageSize() : this.pageNo;
        Sort.Direction sort = Objects.nonNull(pageRequestDto.getSort()) ? pageRequestDto.getSort() : this.sort;
        String sortColumn = Objects.nonNull(pageRequestDto.getSortColumn()) ? pageRequestDto.getSortColumn() : this.sortColumn;

        return PageRequest.of(pageNo, pageSize, sort, sortColumn);
    }

}
