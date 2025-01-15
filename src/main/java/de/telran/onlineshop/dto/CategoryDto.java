package de.telran.onlineshop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.Objects;

@Schema(description = "Сущность категории")
public class CategoryDto {
    @Schema(description = "Уникальный идентификатор категории", example = "2"/*, accessMode = Schema.AccessMode.READ_ONLY*/)
    @PositiveOrZero(message = "Invalid categoryID: должно быть больше или равно 0")
    private long categoryID;

    @Schema(description = "Наименование категории", example = "Новая категория")
    @NotNull
    @NotEmpty(message = "Invalid name: Empty name")
    @Size(min=2, max=30, message = "Invalid name: Must be of 2 - 30 characters")
    private String name;

    public CategoryDto() {
    }

    public CategoryDto(long categoryID, String name) {
        this.categoryID = categoryID;
        this.name = name;
    }

    public long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(long categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryDto category = (CategoryDto) o;
        return categoryID == category.categoryID && Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(categoryID);
        result = 31 * result + Objects.hashCode(name);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryID=" + categoryID +
                ", name='" + name + '\'' +
                '}';
    }
}
