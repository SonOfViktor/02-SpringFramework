package com.epam.esm.config;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.epam.esm")
@EnableAspectJAutoProxy
@Profile("prod")
public class LogConfig {

}
