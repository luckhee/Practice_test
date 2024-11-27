package kr.co.testshortenurlservice.application;

import kr.co.testshortenurlservice.domain.LackOfShortenUrlkeyException;
import kr.co.testshortenurlservice.domain.NotFoundShortenUrlException;
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
        String shortenUrlKey = getUniqueShortenUrlKey(); // save가 일어나기 전 큰 깨달음 허허

        ShortenUrl shortenUrl = new ShortenUrl(orginalUrl, shortenUrlKey);
        createRepository.save(shortenUrl);

        ShortenUrlResponseDto shortenUrlResponseDto = new ShortenUrlResponseDto(shortenUrl);
        return shortenUrlResponseDto;
    }

    public ShortenUrlInformationDto findInformation(String shortenUrlKey) {
        ShortenUrl shortenUrl = createRepository.getInform(shortenUrlKey);


        if(shortenUrl == null) {
            throw new NotFoundShortenUrlException();
        }

        ShortenUrlInformationDto shortenUrlInformationDto = new ShortenUrlInformationDto(shortenUrl);
        return shortenUrlInformationDto;
    }

    public String getOriginalUrlByShortenUrl(String shortenUrlKey) {
        ShortenUrl shortenUrl = createRepository.getInform(shortenUrlKey);

        if(shortenUrl == null) {
            throw new NotFoundShortenUrlException();
        }

        shortenUrl.increasedRedirectCount();
        createRepository.save(shortenUrl);

        String originalUrl = shortenUrl.getOriginalUrl();
        return originalUrl;
    }

    public String getUniqueShortenUrlKey() {
        final int Max_final = 5;
        int count = 0;

        while(count++ < Max_final){
            String shortenUrlkey = ShortenUrl.generateShortenUrlKey();
            ShortenUrl shortenUrl = createRepository.getInform(shortenUrlkey);

            if(null == shortenUrl) {
                return shortenUrlkey; // save가 일어나기 전
            }
        }

        throw new LackOfShortenUrlkeyException();
    }


}
