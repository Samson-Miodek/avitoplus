package ru.sadykoff.avitoplus.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "avito_full_data_ads")
public class AdvertisementFull extends Advertisement {
    public AdvertisementFull(Advertisement advertisement) {
        this.setUrl(advertisement.getUrl());
        this.setCategory(advertisement.getCategory());
        this.setName(advertisement.getName());
        this.setSpecifications(advertisement.getSpecifications());
        this.setPriceCurrency(advertisement.getPriceCurrency());
        this.setPrice(advertisement.getPrice());
        this.setDate(advertisement.getDate());
        this.setPlace(advertisement.getPlace());
        this.setDescription(advertisement.getDescription());
    }
}
