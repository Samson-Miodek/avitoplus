package ru.sadykoff.avitoplus.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.sadykoff.avitoplus.entity.Advertisement;


@Repository
public interface AdvertisementRepository extends ReactiveMongoRepository<Advertisement, String> {
    Mono<Advertisement> findByUrl(String link);
}
