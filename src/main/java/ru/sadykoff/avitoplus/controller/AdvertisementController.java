package ru.sadykoff.avitoplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.sadykoff.avitoplus.dto.AvitoData;
import ru.sadykoff.avitoplus.dto.ResponseAdvertisementInfo;
import ru.sadykoff.avitoplus.service.AvitoDataService;

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
    public Mono<ResponseEntity<ResponseAdvertisementInfo>> getInfo(@RequestParam String link) {
        var advertisement = avitoDataService.findByLink(link);

        return advertisement
                .filter(adv -> adv.getCommonCluster() != null && adv.getCommonInfo() != null)
                .map(adv -> ResponseAdvertisementInfo.builder()
                        .status(200)
                        .message(link)
                        .data(adv.getCommonInfo())
                        .build())
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseAdvertisementInfo.builder()
                        .status(404)
                        .message("No data")
                        .data(null)
                        .build()).map(ResponseEntity::ok));
    }
}
