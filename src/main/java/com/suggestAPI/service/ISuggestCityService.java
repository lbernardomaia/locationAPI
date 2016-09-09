package com.suggestAPI.service;

import java.io.IOException;
import java.net.URISyntaxException;

public interface ISuggestCityService {
    void suggestedCities(String city) throws IOException, URISyntaxException;
}
