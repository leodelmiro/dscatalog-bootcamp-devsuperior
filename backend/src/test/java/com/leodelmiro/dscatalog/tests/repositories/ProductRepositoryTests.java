package com.leodelmiro.dscatalog.tests.repositories;

import com.leodelmiro.dscatalog.entities.Category;
import com.leodelmiro.dscatalog.entities.Product;
import com.leodelmiro.dscatalog.repositories.ProductRepository;
import com.leodelmiro.dscatalog.tests.factory.CategoryFactory;
import com.leodelmiro.dscatalog.tests.factory.ProductFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    private Long existingId;
    private Long nonExistingId;
    private Long countTotalProducts;
    private Long countPcGamerProducts;
    private Long countLivrosCategoryProducts;
    private Pageable pageable;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 1000L;
        countTotalProducts = 25L;
        countPcGamerProducts = 21L;
        countLivrosCategoryProducts = 1L;
        pageable = PageRequest.of(0, 10);
    }

    @Test
    public void findShouldReturnAllProductsWhenCategoryIsNull(){
        String name = "";

        Page<Product> result = productRepository.find(null, name, pageable);

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(countTotalProducts, result.getTotalElements());
    }

    @Test
    public void findShouldReturnAllCategoryProductsWhenCategoryExists(){
        String name = "";
        List<Category> list = new ArrayList<>();
        list.add(CategoryFactory.createCategory());

        Page<Product> result = productRepository.find(list, name, pageable);

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(countLivrosCategoryProducts, result.getTotalElements());
    }

    @Test
    public void findShouldReturnNoneCategoryProductsWhenCategoryNonExists(){
        String name = "";
        List<Category> list = new ArrayList<>();
        list.add(CategoryFactory.createInvalidCategory());

        Page<Product> result = productRepository.find(list, name, pageable);

        Assertions.assertTrue(result.isEmpty());
        Assertions.assertEquals(0L, result.getTotalElements());
    }

    @Test
    public void findShouldReturnAllProductsWhenNameIsEmpty(){

        String name = "";

        Page<Product> result = productRepository.find(null, name, pageable);

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(countTotalProducts, result.getTotalElements());
    }

    @Test
    public void findShouldReturnProductsWhenNameExistsIgnoringCase(){

        String name = "pc gAMeR";

        Page<Product> result = productRepository.find(null, name, pageable);

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(countPcGamerProducts, result.getTotalElements());
    }

    @Test
    public void findShouldReturnProductsWhenNameExists(){

        String name = "PC Gamer";

        Page<Product> result = productRepository.find(null, name, pageable);

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(countPcGamerProducts, result.getTotalElements());
    }

    @Test
    public void saveShouldPersistWithAutoincrementWhenIdIsNull() {

        Product product = ProductFactory.createProduct();
        product.setId(null);

        product = productRepository.save(product);

        Optional<Product> result = productRepository.findById(product.getId());

        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(countTotalProducts + 1L, product.getId());
        Assertions.assertTrue(result.isPresent());
        Assertions.assertSame(result.get(), product);
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {

        productRepository.deleteById(existingId);

        Optional<Product> result = productRepository.findById(existingId);

        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExists() {

        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            productRepository.deleteById(nonExistingId);
        });
    }
}
