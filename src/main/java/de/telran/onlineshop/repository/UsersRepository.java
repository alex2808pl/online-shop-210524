package de.telran.onlineshop.repository;

import de.telran.onlineshop.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    @Query("SELECT u FROM UsersEntity u WHERE u.email=?1")
    List<UsersEntity> getByEmail(String email);

    @Query("SELECT u FROM UsersEntity u WHERE u.refreshToken=?1")
    List<UsersEntity> getByRefreshToken(String refreshToken);
}
