package de.telran.onlineshop.controller;

import de.telran.onlineshop.dto.CategoryDto;
import de.telran.onlineshop.dto.ProductsDto;
import de.telran.onlineshop.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductsService productsService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping  //select
    public List<ProductsDto> getAllCategories() {
        return productsService.getAllProducts();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/filter")
    //?category=1&min_price=1&max_price=10&is_discount=true&sort=price,desc
    public List<ProductsDto> getProductsWithQuery(
            @RequestParam(value = "category", required = false) Long categoryId,
            @RequestParam(value = "min_price", required = false)  Double minPrice,
            @RequestParam(value = "max_price", required = false)  Double maxPrice,
            @RequestParam(value = "is_discount", required = false, defaultValue = "false")  Boolean isDiscount,
            @RequestParam(value = "sort", required = false)  String sort)
    {
        List<ProductsDto> productList = productsService.getProducts(
                categoryId,
                minPrice,
                maxPrice,
                isDiscount,
                sort
        );
        return productList;
    }

}
