package com.leodelmiro.dscatalog.tests.factory;

import com.leodelmiro.dscatalog.dto.CategoryDTO;
import com.leodelmiro.dscatalog.entities.Category;

public class CategoryFactory {

    public static Category createCategory() {
        return new Category(1L, "Livros");
    }

    public static Category createInvalidCategory() {
        return new Category(4L, "Roupas");
    }

    public static CategoryDTO createCategoryDTO() {
        return new CategoryDTO(createCategory());
    }
}
