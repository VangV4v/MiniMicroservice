package com.vang.categoryservice.query.projection;

import com.vang.categoryservice.data.Categories;
import com.vang.categoryservice.data.CategoriesRepository;
import com.vang.categoryservice.query.model.CategoryResponseModel;
import com.vang.categoryservice.query.queries.GetAllCategories;
import com.vang.categoryservice.query.queries.GetByCategoryId;
import org.apache.commons.lang.StringUtils;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryQueryProjection {

    private final CategoriesRepository categoriesRepository;

    public CategoryQueryProjection(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @QueryHandler
    public CategoryResponseModel getCategoryById(GetByCategoryId categoryId) {

        CategoryResponseModel model = new CategoryResponseModel();
        Categories categories = categoriesRepository.findById(categoryId.getCategoryId()).orElse(new Categories());
        if(StringUtils.isBlank(categories.getCategoryid())) {
            model.initialize();
        }else {
            BeanUtils.copyProperties(categories, model);
        }
        return model;
    }

    @QueryHandler
    public List<CategoryResponseModel> getAllCategories(GetAllCategories allCategories) {

        List<Categories> categories = categoriesRepository.findAll();
        List<CategoryResponseModel> models = new ArrayList<>();
        categories.forEach(e -> {
            CategoryResponseModel model = new CategoryResponseModel();
            BeanUtils.copyProperties(e, model);
            models.add(model);
        });
        return models;
    }
}