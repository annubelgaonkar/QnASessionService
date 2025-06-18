package dev.qna.qna_session_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class QnASessionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QnASessionServiceApplication.class, args);
    }

}
