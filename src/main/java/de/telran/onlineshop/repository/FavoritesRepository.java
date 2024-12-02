package de.telran.onlineshop.repository;

import de.telran.onlineshop.entity.FavoritesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritesRepository extends JpaRepository<FavoritesEntity,Long> {

}
