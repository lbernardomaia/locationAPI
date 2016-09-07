package service;

import domain.City;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import service.SuggestCityService;
import util.FileUtil;
import util.PropertyUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class SuggestCityServiceIntegrationTest {
    SuggestCityService suggestCityService;
    PropertyUtil propertyUtil;
    RestTemplate restTemplate;
    FileUtil fileUtil;

    public SuggestCityServiceIntegrationTest(){
        restTemplate = new RestTemplate();
        propertyUtil = new PropertyUtil();
        fileUtil = new FileUtil();
        suggestCityService = new SuggestCityService(restTemplate, propertyUtil, fileUtil);

    }

    @Test
    public void test_with_berlin_city(){
        String city = "berlin";

        List<City> suggestedCities = suggestCityService.getSuggestedCities(city);
        assert suggestedCities.size() > 1;
    }

}
