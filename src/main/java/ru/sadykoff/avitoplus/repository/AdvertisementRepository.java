package ru.sadykoff.avitoplus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.sadykoff.avitoplus.entity.Advertisement;

import java.util.Optional;

@Repository
public interface AdvertisementRepository extends MongoRepository<Advertisement, String> {
    Optional<Advertisement> findByUrl(String link);
}
