package ru.sadykoff.avitoplus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sadykoff.avitoplus.dto.AvitoData;
import ru.sadykoff.avitoplus.entity.Advertisement;
import ru.sadykoff.avitoplus.repository.AdvertisementRepository;

import java.util.Optional;


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
        advertisementFilterService.filter(avitoData);
        var existsAdv = advertisementRepository.findAllById(avitoData.getAdvertisements().stream().map(Advertisement::getUrl).toList());
        int excount = 0;
        for (var advertisement : existsAdv) {
            excount++;
            updateAdvertisement(avitoData, advertisement);
        }
        var result = advertisementRepository.saveAll(avitoData.getAdvertisements());
        System.out.println("Новые " + (avitoData.getAdvertisements().size() - excount) + ", обновлено " + excount);
    }

    private void updateAdvertisement(AvitoData avitoData, Advertisement oldAdvertisement) {
        var list = avitoData.getAdvertisements();

        for (var newAdvertisement : list) {
            if (newAdvertisement.getUrl().equals(oldAdvertisement.getUrl())) {
                newAdvertisement.setFull(oldAdvertisement.isFull());

                //сохраняем наиболее подробное описание категории товара
                if (newAdvertisement.getCategory().size() < oldAdvertisement.getCategory().size()) {
                    newAdvertisement.setCategory(oldAdvertisement.getCategory());
                }

                if (oldAdvertisement.getCommonCluster() != null || oldAdvertisement.getCommonInfo() != null) {
                    newAdvertisement.setCommonCluster(oldAdvertisement.getCommonCluster());
                    newAdvertisement.setDescriptionCluster(oldAdvertisement.getDescriptionCluster());
                    newAdvertisement.setCommonInfo(oldAdvertisement.getCommonInfo());
                    newAdvertisement.setDescriptionInfo(oldAdvertisement.getDescriptionInfo());
                }

                if (oldAdvertisement.isFull()) {
                    newAdvertisement.setSpecifications(oldAdvertisement.getSpecifications());
                    newAdvertisement.setDescription(oldAdvertisement.getDescription());
                }
                break;
            }
        }

    }

    public Optional<Advertisement> findByLink(String link) {
        return advertisementRepository.findByUrl(link);
    }
//
//    public Optional<List<AdvertisementFull>> findAllByCluster(String cluster){
//        return fullAdvertisementRepository.findAllByCluster(cluster);
//    }
}
