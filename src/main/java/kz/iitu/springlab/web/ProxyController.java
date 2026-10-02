package kz.iitu.springlab.web;

import kz.iitu.springlab.springlab.service.CatalogService;
import org.springframework.aop.support.AopUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/lab4")
public class ProxyController {

    private final CatalogService catalogService;

    public ProxyController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/proxy")
    public Map<String, Object> proxy() {

        Map<String, Object> result = new LinkedHashMap<>();

        result.put("class",
                catalogService.getClass().getName());

        result.put("isAopProxy",
                AopUtils.isAopProxy(catalogService));

        result.put("isCglibProxy",
                AopUtils.isCglibProxy(catalogService));

        return result;
    }
}