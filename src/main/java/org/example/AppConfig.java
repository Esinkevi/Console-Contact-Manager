package org.example;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("org.example")
@PropertySource("classpath:application.properties")
public class AppConfig {


    @Bean
    public FileManager fileManager() {
        return new FileManager();
    }


}
