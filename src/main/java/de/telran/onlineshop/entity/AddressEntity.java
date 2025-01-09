package de.telran.onlineshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Addresses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AddressID")
    private long addressId;

    @Column(name = "Post")
    private Integer post;

    @Column(name = "City")
    private String city;

    @Column(name = "Street")
     private String street;

    @Column(name = "Number")
    private String number;


//    @ManyToMany
//    @JoinTable(name = "UsersAddresses",
//            joinColumns = @JoinColumn(name = "AddressID"),
//            inverseJoinColumns = @JoinColumn(name = "UserID"))
//    private Set<UsersEntity> users =  new HashSet<>();


}
