package com.jerry.jerryShop.category;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

	private Long id;
    private String name;
    private Long depth;
    private List<CategoryDTO> children;

    public static CategoryDTO of(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getDepth(),
                category.getChildren().stream().map(CategoryDTO::of).collect(Collectors.toList())
        );
    }
}