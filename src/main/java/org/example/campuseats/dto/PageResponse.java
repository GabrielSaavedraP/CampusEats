package org.example.campuseats.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.query.Page;

@Data
@AllArgsConstructor
public class PageResponse {
    private List<T> content;
    private int page;
    private int size;
    private Long totalElements;
    public static<T> PageResponse<T> from(page<T> spring Page){
        return new PageResponse<>(
                spring Page.getContent(),
                spring Page.getNumber(),
                spring Page.getSize(),
                springPage.getTotalElements()
        );
    }
}
