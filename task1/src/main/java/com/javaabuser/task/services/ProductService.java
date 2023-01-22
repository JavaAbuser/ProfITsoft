package com.javaabuser.task.services;

import com.javaabuser.task.dto.CategoryDTO;
import com.javaabuser.task.dto.ProductDTO;
import com.javaabuser.task.entities.Category;
import com.javaabuser.task.entities.Product;
import com.javaabuser.task.exceptions.IncorrectPagination;
import com.javaabuser.task.exceptions.category.CategoryDoesNotExist;
import com.javaabuser.task.exceptions.product.ProductNotFoundException;
import com.javaabuser.task.repositories.CategoryRepository;
import com.javaabuser.task.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public List<Product> getProducts(String name, String description, String page, String perPage){
        List<Product> products;

        if(name != null && description != null){
            products = findByNameAndDescription(name, description);
        }
        else if(name != null){
            products = findByName(name);
        }
        else if(description != null){
            products = findByDescription(description);
        }
        else {
            products = productRepository.getProducts();
        }
        if(page != null && perPage != null){
            if(Integer.parseInt(page) <= 0 || Integer.parseInt(perPage) < 0){
                throw new IncorrectPagination();
            }
                products = productRepository.getProducts()
                        .stream()
                        .skip((long) (Integer.parseInt(page) - 1) * Integer.parseInt(perPage))
                        .limit(Integer.parseInt(perPage)).collect(Collectors.toList());
        }
        return products;
    }

    public Product getProductById(int id) throws ProductNotFoundException {
        Product product;
        if(productRepository.getProductById(id).isPresent()) {
            product = productRepository.getProductById(id).get();
        } else {
            throw new ProductNotFoundException();
        }
        return product;
    }

    @Transactional
    public void save(Product product){
        addCategory(product);
        productRepository.save(product);
    }
    @Transactional
    public void update(int id, Product product) throws CategoryDoesNotExist {
        String categoryName = product.getCategory().getName();
        if(categoryRepository.getCategoryByName(categoryName).isEmpty()){
            throw new CategoryDoesNotExist();
        }
        productRepository.update(id, product);
    }
    @Transactional
    public void delete(Product product){
        productRepository.delete(product);
    }

    public void addCategory(Product product){
        product.setCategory(categoryRepository.getCategoryByName(product.getCategory().getName()).get());
    }

    public ProductDTO convertToProductDTO(Product product){
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        Category category = categoryRepository.getCategoryByName(product.getCategory().getName()).get();
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        productDTO.setCategory(categoryDTO);

        return productDTO;
    }

    public Product convertToProduct(ProductDTO productDTO){
        Product product = modelMapper.map(productDTO, Product.class);
        Category category = categoryRepository.getCategoryByName(productDTO.getCategory().getName()).get();
        product.setCategory(category);

        return product;
    }

    private List<Product> findByName(String name){
        return productRepository.findByName(name);
    }

    private List<Product> findByDescription(String description){
        return productRepository.findByDescription(description);
    }

    private List<Product> findByNameAndDescription(String name, String description){
        return productRepository.findByNameAndDescription(name, description);
    }
}
