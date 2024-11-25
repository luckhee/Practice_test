package kr.co.testshortenurlservice.presentation;

import kr.co.testshortenurlservice.domain.ShortenUrl;

public class ShortenUrlInformationDto {
    private String originalUrl;
    private String shortenedUrlKey;
    private Long redirectedCount;

    public ShortenUrlInformationDto(ShortenUrl shortenUrl) {
        this.originalUrl = shortenUrl.getOriginalUrl();
        this.shortenedUrlKey = shortenUrl.getShortenUrlKey();
        this.redirectedCount = shortenUrl.getRedirectCount();
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortenedUrlKey() {
        return shortenedUrlKey;
    }

    public Long getRedirectedCount() {
        return redirectedCount;
    }
}
