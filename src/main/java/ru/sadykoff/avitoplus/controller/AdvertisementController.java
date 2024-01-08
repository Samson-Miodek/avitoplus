package ru.sadykoff.avitoplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sadykoff.avitoplus.dto.AvitoData;
import ru.sadykoff.avitoplus.dto.ResponseAdvertisementInfo;
import ru.sadykoff.avitoplus.entity.ClusterInfo;
import ru.sadykoff.avitoplus.service.AvitoDataService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class AdvertisementController {

    private final AvitoDataService avitoDataService;

    @Autowired
    public AdvertisementController(AvitoDataService avitoDataService) {
        this.avitoDataService = avitoDataService;
    }

    @PostMapping("/add")
    public void save(@RequestBody AvitoData avitoData) {
        avitoDataService.add(avitoData);
    }

    @GetMapping("/info")
    public ResponseEntity<ResponseAdvertisementInfo> getInfo(@RequestParam String link) {
        System.out.println(link);
        var advertisementOpt = avitoDataService.findByLink(link);
        if (advertisementOpt.isEmpty() || advertisementOpt.get().getCommonInfo() == null || advertisementOpt.get().getCommonCluster().isEmpty()) {
            var response = ResponseAdvertisementInfo.builder()
                    .status(404)
                    .message("No data")
                    .data(null).build();
            return Optional.of(response)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }
        var advertisement = advertisementOpt.get();
        var response = ResponseAdvertisementInfo.builder()
                .status(200)
                .message(link)
                .data(advertisement.getCommonInfo()).build();
        System.out.println(advertisement.getCommonInfo());
        return Optional.of(response)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
