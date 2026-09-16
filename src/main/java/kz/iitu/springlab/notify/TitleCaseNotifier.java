package kz.iitu.springlab.notify;

import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;

@Component("titlecase")
@Order(3)
public class TitleCaseNotifier implements Notifier {

    @Override
    public String send(String message) {
        return "titlecase: " + toTitleCase(message);
    }

    @Override
    public String channel() {
        return "titlecase";
    }

    private String toTitleCase(String input) {
        String[] words = input.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            if (w.isEmpty()) continue;
            sb.append(Character.toUpperCase(w.charAt(0)))
                    .append(w.substring(1).toLowerCase())
                    .append(" ");
        }
        return sb.toString().trim();
    }
}
