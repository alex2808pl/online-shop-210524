package de.telran.onlineshop.service;

import de.telran.onlineshop.model.Category;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CategoriesService {
    private List<Category> categoryList;

    @PostConstruct
    void init() {
        categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "Продукты"));
        categoryList.add(new Category(2, "Быт.химия>"));
        categoryList.add(new Category(3, "Радиотехника"));
        categoryList.add(new Category(4, "Игрушки"));
        categoryList.add(new Category(5, "Одежда"));
        categoryList.add(new Category(6, "Other"));

        System.out.println("Выполняем логику при создании объекта "+this.getClass().getName());
    }

    public List<Category> getAllCategories() {
        return categoryList;
    }

    public Category getCategoryById(@PathVariable Long id) { ///categories/find/3
        if(id > 7) {
            throw new IllegalArgumentException("Не корректный Id");
        }
        return categoryList.stream()
                .filter(category -> category.getCategoryID()==id)
                .findFirst()
                .orElse(null);
    }

    public Category getCategoryByName(@RequestParam String name) { ///categories/get?name=Other,k=2
        return categoryList.stream()
                .filter(category -> category.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public boolean createCategories(@RequestBody Category newCategory) { //insert
        return categoryList.add(newCategory);
    }

    public Category updateCategories(@RequestBody Category updCategory) { //update
        Category result = categoryList.stream()
                .filter(category -> category.getCategoryID() == updCategory.getCategoryID())
                .findFirst()
                .orElse(null);
        if(result!=null) {
            result.setName(updCategory.getName());
        }
        return result;
    }

    public void deleteCategories(@PathVariable Long id) { //delete
        Iterator<Category> it = categoryList.iterator();
        while (it.hasNext()) {
            Category current = it.next();
            if(current.getCategoryID()==id) {
                it.remove();
            }
        }
    }

    @PreDestroy
    void destroy() {
        categoryList.clear();
        System.out.println("Выполняем логику при окончании работы с  объектом "+this.getClass().getName());
    }
}
