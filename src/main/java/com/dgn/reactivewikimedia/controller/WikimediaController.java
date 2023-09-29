package com.dgn.reactivewikimedia.controller;

import com.dgn.reactivewikimedia.service.WikimediaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("wikimedia")
public class WikimediaController {

    private final WikimediaService wikimediaService;

    public WikimediaController(WikimediaService wikimediaService) {
        this.wikimediaService = wikimediaService;
    }

    @GetMapping
    public ResponseEntity<String> getWikimediaEvent(){
        wikimediaService.sendKafkaWikimediaMessage();

        return new ResponseEntity<>("İşlem Başarılı", HttpStatus.OK);
    }

    @GetMapping(value = "/flux",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<String>> getWikimediaEventKafka(){

        final Flux<String> wikimediaFlux=wikimediaService.getWikimediaFlux();

        return ResponseEntity.ok(wikimediaFlux);
    }
}
