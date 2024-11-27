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


}
