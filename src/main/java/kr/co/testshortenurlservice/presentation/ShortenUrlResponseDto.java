package kr.co.testshortenurlservice.presentation;

import kr.co.testshortenurlservice.domain.ShortenUrl;

public class ShortenUrlResponseDto {
    private String originalUrl;
    private String shortenUrlKey;
//    private Long redirectCount;



    public ShortenUrlResponseDto() {}

    public ShortenUrlResponseDto(ShortenUrl shortenUrl) {
        this.originalUrl = shortenUrl.getOriginalUrl();
        this.shortenUrlKey = shortenUrl.getShortenUrlKey();
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortenUrlKey() {
        return shortenUrlKey;
    }
}
