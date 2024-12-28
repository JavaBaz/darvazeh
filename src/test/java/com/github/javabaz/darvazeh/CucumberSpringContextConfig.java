package com.github.javabaz.darvazeh;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = SpringTestConfig.class
)
public class CucumberSpringContextConfig {

}
