package com.leodelmiro.dscatalog.tests.factory;

import com.leodelmiro.dscatalog.dto.ProductDTO;
import com.leodelmiro.dscatalog.entities.Product;

import java.time.Instant;

public class ProductFactory {

    public static Product createProduct(){
        return new Product(1L, "Phone", "Good Phone", 800.0, "https://img.com/img.png", Instant.parse("2021-10-20T03:00:00Z"));
    }

    public static ProductDTO createProductDTO() {
        return new ProductDTO(createProduct());
    }
}
