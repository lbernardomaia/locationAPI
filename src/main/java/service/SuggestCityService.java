package service;

import domain.City;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.RestTemplate;
import util.EndpointEnum;
import util.FileUtil;
import util.PropertyUtil;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class SuggestCityService implements ISuggestCityService{
    RestTemplate restTemplate;
    PropertyUtil propertyUtil;
    FileUtil fileUtil;

    public SuggestCityService(RestTemplate restTemplate,
                              PropertyUtil propertyUtil,
                              FileUtil fileUtil){
        this.restTemplate = restTemplate;
        this.propertyUtil = propertyUtil;
        this.fileUtil = fileUtil;
    }

    public void suggestedCities(String city) throws IOException, URISyntaxException {
        List<City> suggestedCities = getSuggestedCities(city);
        fileUtil.exportToCSV(suggestedCities);
    }

    protected List<City> getSuggestedCities(String city) {
        List<City> suggestedCities = null;
        if (isEmptyCity(city)) {
            return suggestedCities;
        }

        String propertyValue = EndpointEnum.SUGGEST.getKey();

        String URL = propertyUtil.getValue(propertyValue) + city;

        City[] suggestedCitiesArray = restTemplate.getForObject(URL, City[].class);

        return Arrays.asList(suggestedCitiesArray);
    }

    private boolean isEmptyCity(String city) {
        return StringUtils.isEmpty(city);
    }
}
