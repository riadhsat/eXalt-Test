package com.exalt.katas.integrationtest;

import com.exalt.katas.KatasApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles("test")
@CucumberContextConfiguration
@SpringBootTest(classes = KatasApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberSpringConfiguration {

}
