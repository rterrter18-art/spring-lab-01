package kz.iitu.springlab.notify;

public interface Notifier {
    String send(String message);   // хабарды "жібереді"
    String channel();              // каналдың атын қайтарады
}
