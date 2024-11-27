package de.telran.onlineshop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CartID")
    private long cartId;

    @OneToOne
    @JoinColumn(name = "UserID", referencedColumnName = "userId")
    private UsersEntity user;
}
