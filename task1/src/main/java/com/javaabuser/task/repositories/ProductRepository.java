package com.javaabuser.task.repositories;

import com.javaabuser.task.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> getProducts(){
        return entityManager.createQuery("FROM Product", Product.class).getResultList();
    }

    public Optional<Product> getProductById(int id){
        return entityManager.createQuery("FROM Product WHERE id = : id", Product.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    public void save(Product product){
        entityManager.persist(product);
    }

    public void update(int id, Product product){
            entityManager.createQuery("UPDATE Product SET " +
                            "name = : name, " +
                            "description = : description, " +
                            "category = : category " +
                            "WHERE id = : id")
                    .setParameter("id", id)
                    .setParameter("name", product.getName())
                    .setParameter("description", product.getDescription())
                    .setParameter("category", product.getCategory())
                    .executeUpdate();
    }

    public void delete(Product product){
        entityManager.remove(product);
    }

    public List<Product> findByName(String name){
        return entityManager.createQuery("FROM Product WHERE name = : name", Product.class)
                .setParameter("name", name).getResultList();
    }

    public List<Product> findByDescription(String description){
        return entityManager.createQuery("FROM Product WHERE description = : description", Product.class)
                .setParameter("description", description).getResultList();
    }

    public List<Product> findByNameAndDescription(String name, String description) {
        return entityManager.createQuery("FROM Product WHERE name = : name AND description = : description", Product.class)
                .setParameter("name", name)
                .setParameter("description", description)
                .getResultList();
    }
}
