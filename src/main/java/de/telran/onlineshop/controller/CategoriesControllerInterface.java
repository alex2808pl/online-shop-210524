package de.telran.onlineshop.controller;

import de.telran.onlineshop.dto.CategoryDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.FileNotFoundException;
import java.util.List;

@Tag(name = "Categories", description = "Контроллер для работы с категориями товаров",
        externalDocs = @ExternalDocumentation(
                description = "Ссылка на общую документацию по категориям",
                url = "https://example.com/docs/categories-controller"
        )
)
public interface CategoriesControllerInterface {
    @Operation(
            summary = "Все категории",
            description = "Позволяет получить все категории товаров"
    )
    List<CategoryDto> getAllCategories();

    @Operation(
            summary = "Поиск по Id",
            description = "Позволяет найти информация по идентификатору Id категории товара"
    )
    CategoryDto getCategoryById(
                                @Parameter(description = "Идентификатор категории", required = true, example = "1")
                                Long id) throws FileNotFoundException;

    @Hidden //скрытие данного endpoint из openApi
    void deleteCategories(@PathVariable Long id);
}
