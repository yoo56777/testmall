package com.testmall;

import com.testmall.properties.CustomProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication(exclude = { JacksonAutoConfiguration.class })
@SpringBootApplication
@ComponentScan(basePackages = {"com.testmall"})
@ConfigurationPropertiesScan("com.testmall.properties")
public class TestmallApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestmallApplication.class, args);
	}

}
