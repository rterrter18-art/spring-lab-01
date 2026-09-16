package kz.iitu.springlab;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "Hello, Spring Boot!";
    }

    @GetMapping("/api/hello")
    public Map<String, String> hello(
            @RequestParam(defaultValue = "Guest") String name) {

        return Map.of(
                "message", "Hello, " + name + "!",
                "owner", "Мұратов Мейрамбек Серикович, 2414"
        );
    }

    @GetMapping("/api/info")
    public Map<String, Object> info() {

        return Map.of(
                "application", "Spring Lab 01",
                "student", "Мұратов Мейрамбек Серикович",
                "group", "2414",
                "timestamp", LocalDateTime.now().toString()
        );
    }
}