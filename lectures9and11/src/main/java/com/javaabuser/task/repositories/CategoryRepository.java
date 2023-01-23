package com.javaabuser.task.repositories;

import com.javaabuser.task.entities.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository {
    @PersistenceContext
    private EntityManager entityManager;
    public List<Category> getCategories(){
        return entityManager.createQuery("FROM Category", Category.class)
                .getResultList();
    }

    public Optional<Category> getCategoryByName(String categoryName){
        System.out.println(categoryName);
        return entityManager.createQuery("FROM Category WHERE name=:name", Category.class)
                .setParameter("name", categoryName).getResultStream().findFirst();
    }
}
