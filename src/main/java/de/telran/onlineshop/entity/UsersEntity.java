package de.telran.onlineshop.entity;

import de.telran.onlineshop.entity.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Long userId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Email")
    private String email;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "PasswordHash")
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(name = "Role")
    private Role role;

    @OneToOne(mappedBy = "user")
    private CartEntity cart;

    @OneToMany(mappedBy = "user")
    private Set<FavoritesEntity> favorites = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "UsersAddresses",
            joinColumns = @JoinColumn(name = "UserID"),
            inverseJoinColumns = @JoinColumn(name = "AddressID"))
    private Set<AddressEntity> addresses =  new HashSet<>();
}
