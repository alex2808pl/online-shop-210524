package de.telran.onlineshop.service;

import de.telran.onlineshop.entity.CategoriesEntity;
import de.telran.onlineshop.model.Category;
import de.telran.onlineshop.repository.CategoriesRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor //добавиться конструктор, в который включаться все private final характеристики
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;

    private List<Category> categoryList;

    @PostConstruct
    void init() {
        CategoriesEntity category1 = new CategoriesEntity(null,"Продукты");
        categoriesRepository.save(category1);
        CategoriesEntity category2 = new CategoriesEntity(null,"Быт.химия");
        categoriesRepository.save(category2);
        CategoriesEntity category3 = new CategoriesEntity(null,"Радиотехника");
        categoriesRepository.save(category3);
        CategoriesEntity category4 = new CategoriesEntity(null,"Игрушки");
        categoriesRepository.save(category4);
        CategoriesEntity category5 = new CategoriesEntity(null,"Одежда");
        categoriesRepository.save(category5);
        CategoriesEntity category6 = new CategoriesEntity(null,"Other");
        category6 = categoriesRepository.save(category6);
        category6.setName("Другие");
        categoriesRepository.save(category6);


        System.out.println("Выполняем логику при создании объекта "+this.getClass().getName());
    }

    public List<Category> getAllCategories() {
        List<CategoriesEntity> categoriesEntities = categoriesRepository.findAll();
        return categoriesEntities.stream()
                .map(entity -> new Category(entity.getCategoryId(), entity.getName()))
                .collect(Collectors.toList());
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
