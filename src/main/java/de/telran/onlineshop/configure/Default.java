package de.telran.onlineshop.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class Default {

    @Bean(name = "random1") //явное наименование бина
    public Random  taskRandom1() { // по default имя метода == имени компонента
        return  new Random(10);
    }

    @Bean(name = "random2") //
    public Random  taskRandom2() {
        return  new Random(31);
    }
}
