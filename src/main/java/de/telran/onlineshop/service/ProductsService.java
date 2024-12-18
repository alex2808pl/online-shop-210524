package de.telran.onlineshop.service;


import de.telran.onlineshop.dto.CategoryDto;
import de.telran.onlineshop.dto.ProductsDto;
import de.telran.onlineshop.entity.CategoriesEntity;
import de.telran.onlineshop.entity.ProductsEntity;
import de.telran.onlineshop.repository.CategoriesRepository;
import de.telran.onlineshop.repository.ProductsRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private static final Logger log = LoggerFactory.getLogger(ProductsService.class);
    private final ProductsRepository productsRepository;
    private final CategoriesRepository categoriesRepository;

    //@PostConstruct
    void init() {
//        //Создание продукта и создание категории
//        CategoriesEntity category1 = new CategoriesEntity(null,"Молочные Продукты");
//        category1 = categoriesRepository.save(category1);
//
//        ProductsEntity product1 = new ProductsEntity(null, "Молоко", "Безлактозное", 1.89,
//                "https://s0.rbk.ru/v6_top_pics/resized/600xH/media/img/0/78/756801770042780.webp",
//                0.15, Timestamp.valueOf(LocalDateTime.now()), null, category1);
//        product1 = productsRepository.save(product1);

        //Создание продукта и используем существующую категорию
        CategoriesEntity category2 = categoriesRepository.findById(2L).orElse(null);
        ProductsEntity product2 = new ProductsEntity(null, "Порошок", "для детей", 3.89,
                "https://s0.rbk.ru/v6_top_pics/resized/600xH/media/img/0/78/756801770042780.webp",
                0.15, Timestamp.valueOf(LocalDateTime.now()), null, category2, null);
        productsRepository.save(product2);

        //Создание продукта и используем существующую категорию
        CategoriesEntity category3 = categoriesRepository.findByName("Продукты");
        ProductsEntity product3 = new ProductsEntity(null, "Молоко", "Безлактозное", 1.89,
                "https://s0.rbk.ru/v6_top_pics/resized/600xH/media/img/0/78/756801770042780.webp",
                0.15, Timestamp.valueOf(LocalDateTime.now()), null, category3,null);
        product3 = productsRepository.save(product3);

        //Создание продукта и используем существующую категорию
        ProductsEntity product4 = new ProductsEntity(null, "Сливки", "Безлактозное", 5.89,
                "https://s0.rbk.ru/v6_top_pics/resized/600xH/media/img/0/78/756801770042780.webp",
                0.15, Timestamp.valueOf(LocalDateTime.now()), null, category3,null);
        product4 = productsRepository.save(product4);
      }

    public List<ProductsDto> getAllProducts() {
        List<ProductsEntity> productsEntities = productsRepository.findAll();
        return productsEntities.stream()
                .map(entity -> ProductsDto.builder()
                                        .productId(entity.getProductId())
                                        .name(entity.getName())
                        .build())
                .collect(Collectors.toList());
    }
}
