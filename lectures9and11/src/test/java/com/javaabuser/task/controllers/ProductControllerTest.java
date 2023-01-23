package com.javaabuser.task.controllers;

import com.javaabuser.task.dto.CategoryDTO;
import com.javaabuser.task.dto.ProductDTO;
import com.javaabuser.task.entities.Category;
import com.javaabuser.task.entities.Product;
import com.javaabuser.task.exceptions.product.ProductNotFoundException;
import com.javaabuser.task.services.CategoryService;
import com.javaabuser.task.services.ProductService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockReset;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.aspectj.apache.bcel.classfile.Utility.codeToString;
import static org.aspectj.apache.bcel.classfile.Utility.format;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {
    private String name;
    private String description;
    private String page;
    private String perPage;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductController productController;

    @MockBean
    private RestTemplate restTemplate;

    private CategoryDTO category1;
    private CategoryDTO category2;
    private CategoryDTO category5;

    @BeforeEach
    public void initializeCategories(){
        category1 = new CategoryDTO("clothes");
        category2 = new CategoryDTO("toy");
        category5 = new CategoryDTO("sport");
    }

    private void setUpValues(String name, String description, String page, String perPage){
        this.name = name;
        this.description = description;
        this.page = page;
        this.perPage = perPage;
    }

    @Test
    void getProducts() throws Exception {
        setUpValues(null, null, null, null);

        when(productController.getProducts(name, description, page, perPage)).thenReturn(
                List.of(
                        new ProductDTO("football ball", "football equipment", category5),
                        new ProductDTO("shirt", "black shirt", category1),
                        new ProductDTO("bmw X5 model", "1to21 size model of car", category2)
                ));
//
        mockMvc.perform(get("/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[*].name", containsInAnyOrder(
                        "football ball", "shirt", "bmw X5 model")))
                .andExpect(jsonPath("$[*].description",
                        containsInAnyOrder(
                                "football equipment",
                               "black shirt",
                                "1to21 size model of car")))
                .andDo(print());

        //test with one parameter
        setUpValues("shirt", null, null, null);

        when(productController.getProducts(name, description, page, perPage)).thenReturn(
                List.of(
                        new ProductDTO("shirt", "black shirt", category1)
                ));

        mockMvc.perform(get("/products")
                        .param("name", "shirt")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[*].name", contains("shirt")))
                .andExpect(jsonPath("$[*].description", contains("black shirt")))
                .andExpect(jsonPath("$[*].category.name", contains("clothes")))
                .andDo(print());

        // test with two parameters
        setUpValues("shirt", "black shirt", null, null);

        when(productController.getProducts(name, description, page, perPage)).thenReturn(
                List.of(
                        new ProductDTO("shirt", "black shirt", category1)
                ));

        mockMvc.perform(get("/products")
                        .param("name", "shirt")
                        .param("description", "black shirt")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[*].name", contains("shirt")))
                .andExpect(jsonPath("$[*].description", contains("black shirt")))
                .andExpect(jsonPath("$[*].category.name", contains("clothes")))
                .andDo(print());
    }

    @Test
    void getProductById() throws Exception {
        when(productController.getProductById(3)).thenReturn(
                new ProductDTO("bmw X5 model", "1to21 size model of car", category2)
        );

        mockMvc.perform(get("/products/3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
}

    @Test
    void createProduct() throws Exception {
        String productName = "city2";
        String productDescription = "constructor lego";

        String body = """
       {
           "name": "%s",
           "description": "%s",
           "category": "%s"
       }""".formatted(productName, productDescription, category2);

        ProductDTO product = new ProductDTO(productName, productDescription, category2);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                )
                .andExpect(status().isOk());
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }
}
