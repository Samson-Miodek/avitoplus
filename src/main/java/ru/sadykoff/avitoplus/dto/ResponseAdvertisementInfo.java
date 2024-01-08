package ru.sadykoff.avitoplus.dto;

import lombok.Builder;
import lombok.Data;
import ru.sadykoff.avitoplus.entity.Advertisement;
import ru.sadykoff.avitoplus.entity.ClusterInfo;

import java.util.List;

@Builder
@Data
public class ResponseAdvertisementInfo {
    private Integer status;
    private String message;
    private ClusterInfo data;
    private List<Advertisement> advertisements;
}
