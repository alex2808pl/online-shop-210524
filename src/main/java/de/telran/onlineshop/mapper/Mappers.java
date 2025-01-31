package de.telran.onlineshop.mapper;

import de.telran.onlineshop.configure.MapperUtil;
import de.telran.onlineshop.dto.CartDto;
import de.telran.onlineshop.dto.FavoritesDto;
import de.telran.onlineshop.dto.ProductsDto;
import de.telran.onlineshop.dto.UserDto;
import de.telran.onlineshop.entity.CartEntity;
import de.telran.onlineshop.entity.FavoritesEntity;
import de.telran.onlineshop.entity.ProductsEntity;
import de.telran.onlineshop.entity.UsersEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class Mappers {

    //@Autowired
    private final ModelMapper modelMapper;

    public UserDto convertToUserDto(UsersEntity usersEntity) {
        if(usersEntity==null) return new UserDto();
        modelMapper.typeMap(UsersEntity.class, UserDto.class)
                .addMappings(mapper -> mapper.skip(UserDto::setEmail)); // исключаем этот метод из работы
        UserDto userDto = modelMapper.map(usersEntity, UserDto.class); //автомат
        if (userDto.getPasswordHash()!=null)
            userDto.setPasswordHash("***"); // замещаем данных

        // преобразовываем
        if (usersEntity.getFavorites()!=null) {
            Set<FavoritesDto> favoritesDtoSet = MapperUtil.convertSet(usersEntity.getFavorites(), this::convertToFavoritesDto);
            userDto.setFavorites(favoritesDtoSet);
        }

        CartDto cartDto = convertToCartDto(usersEntity.getCart()); // второй связанный объект
        userDto.setCart(cartDto);
        return userDto;
    }

    public CartDto convertToCartDto(CartEntity cartEntity) {
        CartDto cartDto = null;
        if(cartEntity!=null)
            cartDto = modelMapper.map(cartEntity, CartDto.class); //автомат
        return cartDto;
    }

    public FavoritesDto convertToFavoritesDto(FavoritesEntity favoritesEntity) {
        FavoritesDto favoritesDto = modelMapper.map(favoritesEntity, FavoritesDto.class); //автомат
        favoritesDto.setUser(null);
        return favoritesDto;
    }

    public UsersEntity convertToUserEntity(UserDto userDto) {
        UsersEntity usersEntity = modelMapper.map(userDto, UsersEntity.class); //автомат
        return usersEntity;
    }

    public ProductsDto convertToProductsDto(ProductsEntity products) {
        ProductsDto productsDto = modelMapper.map(products, ProductsDto.class);
        return productsDto;
    }

    public ProductsEntity convertToProducts(ProductsDto productsDto) {
        ProductsEntity products = modelMapper.map(productsDto, ProductsEntity.class);
        return products;
    }

}
