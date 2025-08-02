package org.ht;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class BrowseApplication {

    public static void main( String[] args ) {
        SpringApplication.run(BrowseApplication.class, args);
    }

}
