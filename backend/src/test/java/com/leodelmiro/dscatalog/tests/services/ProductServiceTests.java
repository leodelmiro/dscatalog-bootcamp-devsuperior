package com.leodelmiro.dscatalog.tests.services;

import com.leodelmiro.dscatalog.dto.ProductDTO;
import com.leodelmiro.dscatalog.entities.Product;
import com.leodelmiro.dscatalog.repositories.ProductRepository;
import com.leodelmiro.dscatalog.services.ProductService;
import com.leodelmiro.dscatalog.services.exceptions.DatabaseException;
import com.leodelmiro.dscatalog.services.exceptions.ResourceNotFoundException;
import com.leodelmiro.dscatalog.tests.factory.ProductFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository repository;

    private long existingId;
    private long nonExistingId;
    private long dependentId;
    private Product product;
    private Product emptyProduct;
    private ProductDTO productDTO;
    private PageImpl<Product> page;
    private Page<ProductDTO> pageDTO;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 1000L;
        dependentId = 4L;
        product = ProductFactory.createProduct();
        emptyProduct = ProductFactory.createEmptyProduct();
        productDTO = ProductFactory.createProductDTO();
        page = new PageImpl<>(List.of(product));
        pageDTO = page.map(ProductDTO::new);

        Mockito.when(repository.find(ArgumentMatchers.any(), ArgumentMatchers.anyString(), ArgumentMatchers.any()))
                .thenReturn(page);

        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(product);

        Mockito.when(repository.getOne(existingId)).thenReturn(product);
        Mockito.when(repository.getOne(nonExistingId)).thenThrow(EntityNotFoundException.class);

        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(product));
        Mockito.when(repository.findById(nonExistingId)).thenReturn((Optional.empty()));

        Mockito.doNothing().when(repository).deleteById(existingId);
        Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);
        Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);
    }

    @Test
    public void findAllPagedShouldReturnPage() {
        PageRequest pageRequest = PageRequest.of(0, 12, Sort.Direction.valueOf("ASC"), "name");

        Page<ProductDTO> result = productService.findAllPaged(0L, "", pageRequest);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(pageDTO.getTotalPages(), result.getTotalPages());

        Mockito.verify(repository, Mockito.times(1))
                .find(ArgumentMatchers.any(), ArgumentMatchers.anyString(), ArgumentMatchers.any());
    }

    @Test
    public void findByIdShouldReturnProductDTOWhenIdExist() {
        ProductDTO result = productService.findById(existingId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(productDTO, result);

        Mockito.verify(repository, Mockito.times(1)).findById(existingId);
    }

    @Test
    public void findByIdShouldThrowsResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
           productService.findById(nonExistingId);
        });

        Mockito.verify(repository, Mockito.times(1)).findById(nonExistingId);
    }

    @Test
    public void updateIdShouldReturnProductDTOWhenIdExist() {
        ProductDTO result = productService.update(existingId, productDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(productDTO, result);

        Mockito.verify(repository, Mockito.times(1)).getOne(existingId);
        Mockito.verify(repository, Mockito.times(1)).save(product);
    }

    @Test
    public void updateShouldThrowsResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            productService.update(nonExistingId, productDTO);
        });

        Mockito.verify(repository, Mockito.times(1)).getOne(nonExistingId);
    }

    @Test
    public void deleteShouldThrowDatabaseExceptionWhenIdHasDependent() {

        Assertions.assertThrows(DatabaseException.class, () -> {
            productService.delete(dependentId);
        });

        Mockito.verify(repository, Mockito.times(1)).deleteById(dependentId);
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            productService.delete(nonExistingId);
        });

        Mockito.verify(repository, Mockito.times(1)).deleteById(nonExistingId);
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {
        Assertions.assertDoesNotThrow(() -> {
            productService.delete(existingId);
        });

        Mockito.verify(repository, Mockito.times(1)).deleteById(existingId);
    }

}
