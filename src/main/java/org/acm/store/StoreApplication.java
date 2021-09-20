package org.acm.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreApplication {

    private final StoreRepository repository;

    public StoreApplication(StoreRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }
}
