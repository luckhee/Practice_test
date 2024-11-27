package kr.co.testshortenurlservice.domain;

import java.util.Random;

public class ShortenUrl {
    String originalUrl;
    String shortenUrlKey;
    Long redirectCount;

    public ShortenUrl(){}

    public ShortenUrl(String originalUrl, String shortenUrlKey) {
        this.originalUrl = originalUrl;
        this.shortenUrlKey = shortenUrlKey;
        this.redirectCount = 0L;
    }

//    public ShortenUrl(String originalUrl, String shortenUrlKey, Long redirectCount) {
//        this.originalUrl = originalUrl;
//        this.shortenUrlKey = shortenUrlKey;
//        this.redirectCount = 0L;
//    } 아규먼트를 3개를 사용한 생성자가 없어서 redirectCount가 null로 나옴

    public static String generateShortenUrlKey() {
        String base56Characters = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";
        Random random = new Random();
        StringBuilder shortenUrlKey = new StringBuilder();

        for(int count = 0; count < 8; count++) {
            int base56CharactersIndex = random.nextInt(0, base56Characters.length());
            char base56Character = base56Characters.charAt(base56CharactersIndex);

            shortenUrlKey.append(base56Character);
        }

        return shortenUrlKey.toString();
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortenUrlKey() {
        return shortenUrlKey;
    }

    public Long getRedirectCount() {
        return redirectCount;
    }

    public void increasedRedirectCount() {
        this.redirectCount+=1;
    }
}
