package com.friendmanagement.config;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = { "com.friendmanagement" })
@EnableScheduling
public class MicroServiceStarter extends SpringBootServletInitializer {

    /**
     * This field is required to run this app in the server as a separate web
     * application.
     */
    private static Class<MicroServiceStarter> applicationStarter =
            MicroServiceStarter.class;

    public static void main(String[] args) {
        SpringApplication.run(MicroServiceStarter.class, args);
    }

    /**
     * @return SpringApplicationBuilder
     */
    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder applicationBuilder) {
        return applicationBuilder.sources(applicationStarter);
    }
}
