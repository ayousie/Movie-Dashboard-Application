package fawry.task.admin.dashboard.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class OmdbService {
    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String apiKey;


    public OmdbService(RestTemplate restTemplate,
                       @Value("${app.omdb.base-url}") String baseUrl,
                       @Value("${app.omdb.api-key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }


    public Map search(String query, int page) {
        String url = String.format("%s?apikey=%s&s=%s&page=%d", baseUrl, apiKey, query, page);
        return restTemplate.getForObject(url, Map.class);
    }


    public Map findByImdbId(String imdbId) {
        String url = String.format("%s?apikey=%s&i=%s", baseUrl, apiKey, imdbId);
        return restTemplate.getForObject(url, Map.class);
    }
}
