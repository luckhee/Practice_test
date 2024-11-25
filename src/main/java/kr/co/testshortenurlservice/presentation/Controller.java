package kr.co.testshortenurlservice.presentation;


import jakarta.validation.Valid;
import kr.co.testshortenurlservice.application.ShortenUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    private ShortenUrlService shortenUrlService;

    @Autowired
    public Controller(ShortenUrlService shortenUrlService) {
        this.shortenUrlService = shortenUrlService;
    }


    @RequestMapping(value = "/shortenUrl", method = RequestMethod.POST)
    public ResponseEntity<ShortenUrlResponseDto> createRequest(@Valid @RequestBody ShortenUrlRequestDto shortenUrlRequestDto) {
        ShortenUrlResponseDto shortenUrlResponseDto = shortenUrlService.generateUrl(shortenUrlRequestDto);
        return ResponseEntity.ok(shortenUrlResponseDto);
    }

    @RequestMapping(value = "/{shortenUrlKey}", method = RequestMethod.GET)
    public ResponseEntity<?> redirect() {
        return ResponseEntity.ok().body(null);
    }

    @RequestMapping(value = "/shortenUrl/{shortenUrlKey}" , method = RequestMethod.GET)
    public ResponseEntity<ShortenUrlInformationDto> searchInformation(String shortenUrlKey) {

        ShortenUrlInformationDto shortenUrlInformationDto = shortenUrlService.findInformation(shortenUrlKey);

        return ResponseEntity.ok(shortenUrlInformationDto);
    }
}