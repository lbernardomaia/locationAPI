package com.suggestAPI.service;

import com.suggestAPI.domain.City;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.RestTemplate;
import com.suggestAPI.util.EndpointEnum;
import com.suggestAPI.util.FileUtil;
import com.suggestAPI.util.PropertyUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
       if (isEmptyCity(city)) {
            return new ArrayList<>();
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
