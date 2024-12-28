package com.github.javabaz.darvazeh;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@Log4j2
@TestConfiguration

public class SpringTestConfig {



//        @Bean
//        public CustomLogger customLogger() {
//            return new CustomLogger();
//        }

        @Bean
        public RestTemplate restTemplate() {
            log.info("Creating new RestTemplate bean");
            return new RestTemplate();
        }
    }

