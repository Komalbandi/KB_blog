package com.komalbandi.kb_blog.models;
import com.komalbandi.kb_blog.constants.BlogCategorySort;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlogCategoryRequest {
    private int rowCount;
    private int page;
    private BlogCategorySort sortBy;
    private String orderType;
    private String search;
    private BlogCategorySort searchBy;
}
