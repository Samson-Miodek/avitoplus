package ru.sadykoff.avitoplus.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "advertisments")
public class Advertisement {
    @Id
    private String url;
    private String name;
    private String description;
    private String date;
    private Price price;
    private boolean isFull;
    private List<String> category;
    private List<String> place;
    private List<String> specifications;

    private String commonCluster;
    private String descriptionCluster;

    private ClusterInfo commonInfo;
    private ClusterInfo descriptionInfo;
}
