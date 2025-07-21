package dev.qna.tutorSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TutorSessionServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TutorSessionServiceApplication.class, args);
    }
}
