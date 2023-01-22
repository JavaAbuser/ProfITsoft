package com.javaabuser.task.controllers;

import com.javaabuser.task.dto.ProductDTO;
import com.javaabuser.task.entities.Category;
import com.javaabuser.task.entities.Product;
import com.javaabuser.task.exceptions.category.CategoryDoesNotExist;
import com.javaabuser.task.exceptions.product.ProductNotFoundException;
import com.javaabuser.task.services.CategoryService;
import com.javaabuser.task.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<ProductDTO> getProducts(@RequestParam(value = "name", required = false) String name,
                                                        @RequestParam(value = "description", required = false) String description,
                                                        @RequestParam(value = "page", required = false) String page,
                                                        @RequestParam(value = "per_page", required = false) String perPage){
        return productService.getProducts(name, description, page, perPage)
                .stream()
                .map(productService::convertToProductDTO)
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable int id) throws ProductNotFoundException {
        ProductDTO productDTO = productService.convertToProductDTO(productService.getProductById(id));

        return productDTO;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createProduct(@RequestBody ProductDTO productDTO){
        Product product = productService.convertToProduct(productDTO);
        Category category = categoryService.getCategoryByName(productDTO.getCategory().getName());
        product.setCategory(category);
        productService.save(product);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public void updateProduct(@PathVariable int id, @RequestBody ProductDTO productDTO) throws CategoryDoesNotExist {
        Product product = productService.convertToProduct(productDTO);
        productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) throws ProductNotFoundException {
        productService.delete(productService.getProductById(id));
    }
}
