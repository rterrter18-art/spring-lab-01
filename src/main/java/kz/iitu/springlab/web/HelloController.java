package kz.iitu.springlab.web;

import kz.iitu.springlab.notify.NotificationService;
import kz.iitu.springlab.lifecycle.LifecycleDemo;
import kz.iitu.springlab.scope.TicketOffice;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HelloController {
    private final NotificationService notifications;
    private final LifecycleDemo lifecycle;
    private final TicketOffice ticketOffice;

    public HelloController(NotificationService notifications,
                           LifecycleDemo lifecycle,
                           TicketOffice ticketOffice) {
        this.notifications = notifications;
        this.lifecycle = lifecycle;
        this.ticketOffice = ticketOffice;
    }

    @GetMapping("/hello")
    public Map<String, String> hello(@RequestParam(defaultValue = "Guest") String name) {
        return Map.of(
                "message", "Hello, " + name + "!",
                "owner", "Мұратов Мейрамбек Серикович, 2414"
        );
    }

    @GetMapping("/lab2/notify")
    public Map<String, Object> notify(@RequestParam(defaultValue = "Hello") String text) {
        return Map.of(
                "primary", notifications.viaPrimary(text),
                "console", notifications.viaConsole(text),
                "all", notifications.viaAll(text),
                "beanNames", notifications.names()
        );
    }

    @GetMapping("/lab2/lifecycle")
    public List<String> lifecycle() {
        return lifecycle.events();
    }

    @GetMapping("/lab2/scopes")
    public Map<String, Object> scopes() {
        return ticketOffice.demo();
    }

    // 🔹 Жеке тапсырма (Variant 11)
    @GetMapping("/lab2/custom")
    public Map<String, Object> custom(@RequestParam(defaultValue = "Test") String text,
                                      @RequestParam(defaultValue = "titlecase") String qualifier) {
        if (!notifications.names().contains(qualifier)) {
            return Map.of("error", "No such notifier: " + qualifier);
        }
        return Map.of(
                "qualifier", qualifier,
                "result", notifications.viaAll(text).stream()
                        .filter(s -> s.startsWith(qualifier))
                        .findFirst()
                        .orElse("No match")
        );
    }
}
