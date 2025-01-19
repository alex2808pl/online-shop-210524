package de.telran.onlineshop.scheduled;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@EnableAsync //включаем многопоточность
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = false) // позволяет вкл/выкл
public class ScheduledConfig {
}
