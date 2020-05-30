/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static final String ADMIN_TOKEN = "wpGRmTqJtfBggXO1QNeaZFvJ7cxViOluYPAWC1UttCvQOH6XX6Vil1Ry3pR3G6MTIQ3qQfNUPmUhQjOVznWci1ZOzfnq0RDB1qTqxmfdmPevstFVfHYGxrX9cNXlAvEeC3K7WFy5oocLOPRRS0HDbZmEGL64s52MH5CRqvQRgAnur2zUuENoe3Ow8MPeWxcTWz1cxbVnqZ4uf9bXIRI8yHG5ZUvO79LvaacXc2Ienk86axpAcmgYW7v1iwvj6uq";
    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
