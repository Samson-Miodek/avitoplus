package ru.sadykoff.avitoplus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sadykoff.avitoplus.dto.AvitoData;
import ru.sadykoff.avitoplus.entity.Advertisement;
import ru.sadykoff.avitoplus.repository.AdvertisementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AvitoDataService {

    private final AdvertisementRepository advertisementRepository;

    @Autowired
    public AvitoDataService(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    public void saveAll(AvitoData avitoData) {
        advertisementRepository.saveAll(avitoData.getAdvertisements());
    }

    public List<Advertisement> getAll() {
        return advertisementRepository.findAll();
    }

    public Optional<Advertisement> findByLink(String link) {
        return advertisementRepository.findByUrl(link);
    }
}
