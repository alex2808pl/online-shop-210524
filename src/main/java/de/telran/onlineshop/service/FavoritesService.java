package de.telran.onlineshop.service;

import de.telran.onlineshop.entity.FavoritesEntity;
import de.telran.onlineshop.entity.ProductsEntity;
import de.telran.onlineshop.entity.UsersEntity;
import de.telran.onlineshop.repository.FavoritesRepository;
import de.telran.onlineshop.repository.ProductsRepository;
import de.telran.onlineshop.repository.UsersRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FavoritesService {
    private final UsersRepository usersRepository;
    private final ProductsRepository productsRepository;
    private final FavoritesRepository favoritesRepository;

    @PostConstruct
    void init() {
        UsersEntity user1 = usersRepository.findById(1L).orElse(null);
        ProductsEntity product1 = productsRepository.findById(2L).orElse(null);
        FavoritesEntity favorite1 = new FavoritesEntity(null, user1, product1);
        favoritesRepository.save(favorite1);

        ProductsEntity product2 = productsRepository.findById(1L).orElse(null);
        FavoritesEntity favorite2 = new FavoritesEntity(null, user1, product2);
        favoritesRepository.save(favorite2);
    }


}
