package kr.co.testshortenurlservice.domain;


public interface createRepositoryIF {
    void save(ShortenUrl shortenUrl);

    ShortenUrl getInform(String shortenUrlKey);
}
