package kr.co.testshortenurlservice.application;

import kr.co.testshortenurlservice.domain.ShortenUrl;
import kr.co.testshortenurlservice.infrastructure.createRepository;
import kr.co.testshortenurlservice.presentation.ShortenUrlInformationDto;
import kr.co.testshortenurlservice.presentation.ShortenUrlRequestDto;
import kr.co.testshortenurlservice.presentation.ShortenUrlResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShortenUrlService {


    private createRepository createRepository;

    @Autowired
    ShortenUrlService(createRepository createRepository) {
        this.createRepository = createRepository;
    }

    public ShortenUrlResponseDto generateUrl(ShortenUrlRequestDto shortenUrlRequestDto) {
        String orginalUrl = shortenUrlRequestDto.getOriginalUrl();
        String shortenUrlKey = ShortenUrl.generateShortenUrlKey();

        ShortenUrl shortenUrl = new ShortenUrl(orginalUrl, shortenUrlKey);
        createRepository.save(shortenUrl);

        ShortenUrlResponseDto shortenUrlResponseDto = new ShortenUrlResponseDto(shortenUrl);
        return shortenUrlResponseDto;
    }

    public ShortenUrlInformationDto findInformation(String shortenUrlKey) {
        ShortenUrl shortenUrl = createRepository.getInform(shortenUrlKey);

        ShortenUrlInformationDto shortenUrlInformationDto = new ShortenUrlInformationDto(shortenUrl);
        return shortenUrlInformationDto;
    }

    public String getOriginalUrlByShortenUrl(String shortenUrlKey) {
        ShortenUrl shortenUrl = createRepository.getInform(shortenUrlKey);
        shortenUrl.increasedRedirectCount();
        createRepository.save(shortenUrl);

        String originalUrl = shortenUrl.getOriginalUrl();
        return originalUrl;


    }

//    private String getUniqueShortenUrlKey() {
//        final int MAX_RETRY_COUNT = 5;
//        int count = 0;
//
//        while(count++ < MAX_RETRY_COUNT) {
//            String shortenUrlKey = ShortenUrl.generateShortenUrlKey();
//            ShortenUrl shortenUrl = shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlKey);
//
//            if(null == shortenUrl)
//                return shortenUrlKey;
//        }
//
//        throw new LackOfShortenUrlKeyException();
//    }
}
