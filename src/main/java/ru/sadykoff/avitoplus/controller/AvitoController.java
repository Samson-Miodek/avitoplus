package ru.sadykoff.avitoplus.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sadykoff.avitoplus.dto.AvitoData;
import ru.sadykoff.avitoplus.entity.Advertisement;
import ru.sadykoff.avitoplus.entity.AdvertisementFull;
import ru.sadykoff.avitoplus.repository.FullAdvertisement;
import ru.sadykoff.avitoplus.service.AvitoDataService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class AvitoController {

    private final AvitoDataService avitoDataService;

    @Autowired
    private FullAdvertisement fullAdvertisement;

    @Autowired
    public AvitoController(AvitoDataService avitoDataService) {
        this.avitoDataService = avitoDataService;
    }

    @ApiOperation("Сохраняет объявление в бд")
    @PostMapping("/add")
    public ResponseEntity<String> save(@RequestBody AvitoData avitoData, HttpServletRequest httpServletRequest) {
        System.out.println(httpServletRequest.getRemoteAddr());
        System.out.println(httpServletRequest.getRemoteHost());
        System.out.println(httpServletRequest.getRemotePort());
        String clientIP = httpServletRequest.getHeader("X-Real-IP");
        System.out.println(clientIP);
        System.out.println();
        for(var x : avitoData.getAdvertisements())
            if(x.getSpecifications() != null)
                fullAdvertisement.save(new AdvertisementFull(x));

        avitoDataService.saveAll(avitoData);
        return ResponseEntity.ok("hello, world!");
//        return avitoData;
    }

    @ApiOperation("Получить подсчитанные данные по объявлению. Пока просто возвращает объявление по уникальной ссылке или null")
    @GetMapping("/info/{link}")
    public ResponseEntity<Optional<Advertisement>> getInfo(@PathVariable String link) {
        var res = avitoDataService.findByLink(link);
        return Optional.ofNullable(res)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//    @GetMapping("/all"-)
//    public List<Advertisement> all(){
//        return avitoDataService.getAll();
//    }
}
