package ru.sadykoff.avitoplus.dto;

import lombok.Data;
import ru.sadykoff.avitoplus.entity.Advertisement;

import java.util.List;

@Data
public class AvitoData {
    private long count;
    private List<Advertisement> advertisements;
}
