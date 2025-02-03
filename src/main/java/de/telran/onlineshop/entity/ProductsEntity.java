package de.telran.onlineshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "Products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ProductsEntity {
    @Id
    @Column(name = "ProductID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Price")
    private Double price;

//    @Column(name = "CategoryID")
//    private Long categoryId;

    @Column(name = "ImageURL")
    private String imageUrl;

    @Column(name = "DiscountPrice")
    private Double discountPrice;

    @Column(name = "CreatedAt")
    private Timestamp createdAt;

    @Column(name = "UpdatedAt")
    private Timestamp updatedAt;

    @ManyToOne//(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "CategoryID") // имя колонки для связи с CategoriesEntity
    private CategoriesEntity category;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private Set<FavoritesEntity> favorites = new HashSet<>();

//    @ManyToMany
//    @JoinTable(name = "category_product",
//            joinColumns = @JoinColumn(name = "ProductID"),
//            inverseJoinColumns = @JoinColumn(name = "CategoryID"))
//    private Set<CategoriesEntity> categories =  new HashSet<>();

}
