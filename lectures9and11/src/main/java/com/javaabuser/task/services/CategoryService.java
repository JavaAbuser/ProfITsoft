package com.javaabuser.task.services;

import com.javaabuser.task.dto.CategoryDTO;
import com.javaabuser.task.entities.Category;
import com.javaabuser.task.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public List<Category> getCategories(){
        return categoryRepository.getCategories();
    }

    public Category getCategoryByName(String categoryName){
        Optional<Category> category = categoryRepository.getCategoryByName(categoryName);
        if(category.isEmpty()){
            System.out.println("category is null");
        }
        return category.get();
    }

    public CategoryDTO convertToCategoryDTO(Category category){
        return modelMapper.map(category, CategoryDTO.class);
    }

    public Category convertToCategory(CategoryDTO categoryDTO){
        return modelMapper.map(categoryDTO, Category.class);
    }
}
