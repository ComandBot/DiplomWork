package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.service.AdService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {
//    private final AdService adService;
//
//    public AdsController(AdService adService) {
//        this.adService = adService;
//    }

    @GetMapping() // todo будет возвращать ResponseEntity, временно void
    public void getAllAds() {

    }

    @PostMapping()  // todo будет возвращать ResponseEntity
    public String setAds() {
        return null;
    }

    @GetMapping("{id}") // todo будет возвращать ResponseEntity, временно void
    public void getAds() {

    }

    @DeleteMapping("/{id}")
    public void deleteAds() {

    }

    @PatchMapping("/{id}") // todo будет возвращать ResponseEntity, временно void
    public void updateAds() {

    }

    @GetMapping("/me") // todo будет возвращать ResponseEntity, временно void
    public void getAuthUserAds() {

    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    // todo будет возвращать ResponseEntity, временно void
    public void updateImageAds() {

    }
}
