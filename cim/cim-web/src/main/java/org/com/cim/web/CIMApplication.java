package org.com.cim.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"org.com.cim.data.dao"})
@EntityScan(basePackages = {"org.com.cim.data.domain"})
@ComponentScan(basePackages = {"org.com.cim"})
@EnableScheduling
public class CIMApplication extends SpringBootServletInitializer {
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CIMApplication.class);
    }
	
    public static void main(String[] args) {
        SpringApplication.run(CIMApplication.class, args);
    }
}
