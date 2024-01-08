package ru.sadykoff.avitoplus.service;

import org.springframework.stereotype.Service;
import ru.sadykoff.avitoplus.dto.AvitoData;

@Service
public class AdvertisementFilterService {

    public static final String HTTPS_WWW_AVITO_RU = "https://www.avito.ru/";

    public AvitoData filter(AvitoData avitoData) {
        var filteredAdv = avitoData.getAdvertisements().stream()
                .filter(adv -> adv.getUrl().startsWith(HTTPS_WWW_AVITO_RU))
                .peek(adv -> {
                    var url = adv.getUrl();
                    url = url.substring(0,!url.contains("?") ? url.length() : url.indexOf("?"));
                    adv.setUrl(url);
                })
                .toList();
        avitoData.setAdvertisements(filteredAdv);
        avitoData.setCount(filteredAdv.size());
        return avitoData;
    }
}
