package ru.sadykoff.avitoplus.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import io.swagger.annotations.ApiModel;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "avito_raw_data_ads")
//@ApiModel(description = "data model of author entity")
public class Advertisement {
    @Id
    private String url;


    private List<String> category;

    private String name;
    private String specifications;
    private String description;
    private String priceCurrency;
    private String price;
    private String date;
    private String place;
}
