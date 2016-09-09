package com.suggestAPI.service;

import com.suggestAPI.domain.City;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import com.suggestAPI.util.FileUtil;
import com.suggestAPI.util.PropertyUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SuggestCityServiceUnitTest {
    SuggestCityService suggestCityService;
    PropertyUtil propertyUtilMock;
    RestTemplate restTemplateMock;
    FileUtil fileUtilMock;

    public SuggestCityServiceUnitTest() throws IOException, URISyntaxException {
        restTemplateMock = mock(RestTemplate.class);

        City [] cities = new City[1];
        cities[0] = new City();

        when(restTemplateMock.getForObject(anyString(), any(Class.class))).thenReturn(cities);

        propertyUtilMock = mock(PropertyUtil.class);
        when(propertyUtilMock.getValue(anyString())).thenReturn("");

        fileUtilMock = mock(FileUtil.class);
        when(fileUtilMock.exportToCSV(anyListOf(City.class))).thenReturn(true);

        suggestCityService = new SuggestCityService(restTemplateMock,
                                                    propertyUtilMock,
                                                    fileUtilMock);

    }

    @Test
    public void test_with_one_city() throws IOException, URISyntaxException {
        String city = "berlin";
        List<City> suggestedCities = suggestCityService.getSuggestedCities(city);
        assert suggestedCities.size() == 1;
    }

    @Test
    public void test_with_empty_city() throws IOException, URISyntaxException {
        String city = "";
        List<City> suggestedCities = suggestCityService.getSuggestedCities(city);
        assert suggestedCities.isEmpty();
    }

}
