package com.epam.esm.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.epam.esm")
@EnableAspectJAutoProxy
@Profile("prod")
public class LogConfig {

    @Bean
    public Logger logger() {
        return LogManager.getLogger();
    }
}
