package de.telran.onlineshop.scheduled;

import de.telran.onlineshop.repository.ProductsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class SheduledService {

    @Autowired
    private ProductsRepository productsRepository;

    @Async //код метода будет выполняться в отдельном потоке
//    @Scheduled(initialDelay = 10000, //задержка старта
//               fixedDelay=5000 //пертод срабатывания
//              ) //Новое задание всегда будет ждать завершения предыдущего задания

    @Scheduled(fixedDelayString="${fixedDelay.in.milliseconds}") // берет время из параметров
    public void scheduledTaskFixedDelay() throws InterruptedException {
        log.error("Пример работы scheduledTask (fixedDelay) -> "+ LocalDateTime.now());
        Thread.sleep(3000);
    }

   // @Async //код метода будет выполняться в отдельном потоке
    @Scheduled(fixedRate=5000) //Время выполнения метода не учитывается при решении, когда начинать следующее задание
    public void scheduledTaskFixedRate() throws InterruptedException {
        log.info("Пример работы scheduledTask (fixedRate) -> "+ LocalDateTime.now());
        Thread.sleep(3000);
    }


    //@Scheduled(cron="15 * * * * *") //каждую минуту на 15 секунде
    @Scheduled(cron = "${cron.expression}")
    public void scheduledTaskCron() throws InterruptedException {
        log.info("Пример работы scheduledTask (Cron) -> "+ LocalDateTime.now());
    }

}
