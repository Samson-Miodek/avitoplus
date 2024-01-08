package ru.sadykoff.avitoplus.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.sadykoff.avitoplus.entity.Advertisement;

import java.util.List;

@Service
public class AdvertisementFilterService {

    public static final String HTTPS_WWW_AVITO_RU = "https://www.avito.ru/";

    public Flux<Advertisement> filter(List<Advertisement> advertisements) {
        return Flux.fromIterable(advertisements)
                .filter(adv -> adv.getUrl().startsWith(HTTPS_WWW_AVITO_RU))
                .map(adv -> {
                    var url = adv.getUrl();
                    url = url.substring(0,!url.contains("?") ? url.length() : url.indexOf("?"));
                    adv.setUrl(url);
                    return adv;
                });
    }
}
