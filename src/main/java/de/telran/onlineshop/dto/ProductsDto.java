package de.telran.onlineshop.dto;

import de.telran.onlineshop.entity.CategoriesEntity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class ProductsDto {

    private Long productId;

    private String name;

    private String description;

    private Double price;

    private String imageUrl;

    private Double discountPrice;

    private Timestamp createdAt;

    private Timestamp updatedAt;

}
