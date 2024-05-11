package com.vang.categoryservice.command.event;

import com.vang.categoryservice.data.Categories;
import com.vang.categoryservice.data.CategoriesRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.vang.minimicroservice.method.MethodCommon;

@Component
public class CategoryEventsHandler {

    private final CategoriesRepository categoriesRepository;

    @Autowired
    public CategoryEventsHandler(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @EventHandler
    public void handle(CategoryCreatedEvent event) {
        event.setCategoryid(autoGenerateId());
        Categories categories = new Categories();
        BeanUtils.copyProperties(event, categories);
        categoriesRepository.save(categories);
    }

    @EventHandler
    public void handle(CategoryUpdatedEvent event) {
        Categories categories = new Categories();
        BeanUtils.copyProperties(event, categories);
        categoriesRepository.save(categories);
    }

    @EventHandler
    public void handle(CategoryDeletedEvent event) {
        categoriesRepository.deleteById(event.getCategoryid());
    }

    private String autoGenerateId() {
        String latestId = categoriesRepository.getLatestId();
        int id = 0;
        if(StringUtils.isEmpty(latestId)) {
            return "CATEGORY0001";
        }
        id = Integer.parseInt(latestId.substring(MethodCommon.getIndexById(latestId)));
        if(id > 0 && id < 9) {
            return "CATEGORY000"+(id + 1);
        }else if(id >= 9 && id < 99) {
            return "CATEGORY00"+(id + 1);
        }else if(id >= 99 && id < 999) {
            return "CATEGORY0"+(id + 1);
        }else {
            return "CATEGORY"+(id + 1);
        }
    }
}