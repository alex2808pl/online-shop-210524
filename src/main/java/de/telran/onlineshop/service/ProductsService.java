package de.telran.onlineshop.service;


import de.telran.onlineshop.dto.CategoryDto;
import de.telran.onlineshop.dto.ProductsDto;
import de.telran.onlineshop.entity.CategoriesEntity;
import de.telran.onlineshop.entity.ProductsEntity;
import de.telran.onlineshop.repository.CategoriesRepository;
import de.telran.onlineshop.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private static final Logger log = LoggerFactory.getLogger(ProductsService.class);
    private final ProductsRepository productsRepository;

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
