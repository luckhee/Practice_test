package kr.co.testshortenurlservice.infrastructure;



import kr.co.testshortenurlservice.domain.ShortenUrl;
import kr.co.testshortenurlservice.domain.createRepositoryIF;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Repository
public class createRepository implements createRepositoryIF {

    Map<String, ShortenUrl> collector = new HashMap();

    @Override
    public void save(ShortenUrl shortenUrl) {
        String shortenUrlKey = shortenUrl.getShortenUrlKey();
        collector.put(shortenUrlKey, shortenUrl);

    }

    @Override
    public ShortenUrl getInform(String shortenUrlKey) {

        ShortenUrl inform = collector.get(shortenUrlKey);

        return inform;
    }
}
