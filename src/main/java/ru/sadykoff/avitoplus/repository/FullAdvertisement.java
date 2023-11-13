package ru.sadykoff.avitoplus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.sadykoff.avitoplus.entity.Advertisement;
import ru.sadykoff.avitoplus.entity.AdvertisementFull;

@Repository
public interface FullAdvertisement extends MongoRepository<AdvertisementFull, String> {
}