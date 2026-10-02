package kz.iitu.springlab.springlab.service;

import org.springframework.stereotype.Service;

@Service
public class CatalogLookupService {

    public String findById(long id) {
        sleep(50);
        return "Item no. " + id;
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}