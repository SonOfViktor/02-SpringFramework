package com.epam.esm.config;

import com.epam.esm.aspect.ServiceAspect;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.epam.esm")
@EnableAspectJAutoProxy
@Profile("prod")
public class LogConfig {

//    @Bean
//    public ServiceAspect serviceAspect() {
//        return new ServiceAspect();
//    }
}
