package de.telran.onlineshop.entity.enums;

public enum Status {
    CREATED("Создан"),
    CANCEL("Отменено"),
    WAIT_PAYMENT("Ожидает оплаты"),
    PAID("Оплачен"),
    ON_THE_WAY("В пути"),
    DELIVERED("Доставлено");

    private String title;

    Status(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

//Создан
//Ожидает оплаты - заказ добавлен в корзину и ожидает оплаты
//Оплачен - заказ оплачен
//В пути - транспортировка товара до пользователя
//Доставлено - курьер доставил товар (Конечный статус)
//Отменено - пользователь отменил заказ

//Статус Отменено может быть присвоен только если товар в одном из статусов:
//Ожидает оплаты
//Создан


