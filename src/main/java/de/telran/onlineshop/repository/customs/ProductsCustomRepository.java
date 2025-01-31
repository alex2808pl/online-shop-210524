package de.telran.onlineshop.repository.customs;

import de.telran.onlineshop.entity.CategoriesEntity;
import de.telran.onlineshop.entity.ProductsEntity;

import java.util.List;

public interface ProductsCustomRepository {
    List<ProductsEntity> findProductsByFilter(CategoriesEntity category, Double minPrice, Double maxPrice,
                                              Boolean isDiscount, String sort); // price DESC, name

}
