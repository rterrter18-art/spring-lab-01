package kz.iitu.springlab.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
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

    // ✅ Individual Assignment (Variant 11)
    @GetMapping("/api/time")
    public Map<String, Object> getTime(@RequestParam(required = false) String zone) {
        if (zone == null || zone.isBlank()) {
            return Map.of("error", "Parameter 'zone' is required");
        }

        try {
            ZoneId zoneId = ZoneId.of(zone);
            ZonedDateTime now = ZonedDateTime.now(zoneId);
            ZoneOffset offset = now.getOffset();

            return Map.of(
                    "zone", zone,
                    "currentTime", now.toString(),
                    "utcOffset", offset.toString()
            );
        } catch (Exception e) {
            return Map.of("error", "Invalid time zone: " + zone);
        }
    }
}
