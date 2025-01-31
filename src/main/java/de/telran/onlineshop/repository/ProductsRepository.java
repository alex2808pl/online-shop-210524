package de.telran.onlineshop.repository;

import de.telran.onlineshop.entity.ProductsEntity;
import de.telran.onlineshop.repository.customs.ProductsCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity, Long>, ProductsCustomRepository {

}
