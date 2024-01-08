package ru.sadykoff.avitoplus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.sadykoff.avitoplus.dto.AvitoData;
import ru.sadykoff.avitoplus.entity.Advertisement;
import ru.sadykoff.avitoplus.repository.AdvertisementRepository;


@Service
public class AvitoDataService {

    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementFilterService advertisementFilterService;

    @Autowired
    public AvitoDataService(AdvertisementRepository advertisementRepository, AdvertisementFilterService advertisementFilterService) {
        this.advertisementRepository = advertisementRepository;
        this.advertisementFilterService = advertisementFilterService;
    }

    public void add(AvitoData avitoData) {
        advertisementFilterService
                .filter(avitoData.getAdvertisements())
                .flatMap(this::updateOrSaveAdvertisement)
                .subscribe();
    }

    private Mono<Advertisement> updateOrSaveAdvertisement(Advertisement newAdvertisement) {
        return advertisementRepository
                .findByUrl(newAdvertisement.getUrl())
                .flatMap(exAdv -> {
                    //сохраняем наиболее подробное описание категории товара
                    if (newAdvertisement.getCategory().size() < exAdv.getCategory().size()) {
                        newAdvertisement.setCategory(exAdv.getCategory());
                    }

                    //чтобы новое объявление не обнулило кластер
                    if (exAdv.getCommonCluster() != null || exAdv.getCommonInfo() != null) {
                        newAdvertisement.setCommonCluster(exAdv.getCommonCluster());
                        newAdvertisement.setDescriptionCluster(exAdv.getDescriptionCluster());
                        newAdvertisement.setCommonInfo(exAdv.getCommonInfo());
                        newAdvertisement.setDescriptionInfo(exAdv.getDescriptionInfo());
                    }

                    //чтобы новое объявление не обнулило характеристики и описание
                    if (exAdv.isFull()) {
                        newAdvertisement.setFull(true);
                        newAdvertisement.setSpecifications(exAdv.getSpecifications());
                        newAdvertisement.setDescription(exAdv.getDescription());
                    }
                    return advertisementRepository.save(newAdvertisement);
                })
                .switchIfEmpty(advertisementRepository.save(newAdvertisement));
    }


    public Mono<Advertisement> findByLink(String link) {
        return advertisementRepository.findByUrl(link);
    }
}
