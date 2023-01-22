package com.javaabuser.task.dto;

public class ProductDTO {
    private String name;
    private String description;
    private CategoryDTO category;

    public ProductDTO() {
    }

    public ProductDTO(String name, String description, CategoryDTO category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }
}
