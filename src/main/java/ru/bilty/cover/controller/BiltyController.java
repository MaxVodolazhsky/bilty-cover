package ru.bilty.cover.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bilty.cover.model.BoxingLink;
import ru.bilty.cover.model.UnboxingLink;
import ru.bilty.cover.service.LinkService;

@RestController
@RequiredArgsConstructor
public class BiltyController {

    private final LinkService linkService;

    @PostMapping(value = "/boxing", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> boxingUrl(@RequestBody final UnboxingLink unboxingLink) {
        return ResponseEntity.ok(linkService.boxingLink(unboxingLink).getBoxingLink());
    }

    @PostMapping(value = "/unboxing", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> unboxingUrl(@RequestBody final BoxingLink boxingLink) {
        return ResponseEntity.ok(linkService.unboxingLink(boxingLink).getUnboxingLink());
    }
}
