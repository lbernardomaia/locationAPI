package com.suggestAPI;

import org.springframework.web.client.RestTemplate;
import com.suggestAPI.service.ISuggestCityService;
import com.suggestAPI.service.impl.SuggestCityServiceImpl;
import com.suggestAPI.util.FileUtil;
import com.suggestAPI.util.PropertyUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Logger;

public class SuggestAPI {
    private static final Logger LOGGER = Logger.getLogger(SuggestAPI.class.getName() );

    ISuggestCityService suggestCityService;
    PropertyUtil propertyUtil;
    RestTemplate restTemplate;
    FileUtil fileUtil;

    public SuggestAPI(){
        restTemplate = new RestTemplate();
        propertyUtil = new PropertyUtil();
        fileUtil = new FileUtil();
        suggestCityService = new SuggestCityServiceImpl(restTemplate, propertyUtil, fileUtil);
    }

    public static void main (String args []){
        try{
            if (args.length == 0){
                System.exit(0);
            }

            SuggestAPI suggestAPI = new SuggestAPI();
            suggestAPI.suggestCityService.suggestedCities(args[0]);
        }catch (URISyntaxException e){
            LOGGER.severe(String.format("An error during get the URI. %s", e.getMessage()));
        }catch (IOException e){
            LOGGER.severe(String.format("An error during export CSV. %s", e.getMessage()));
        }catch (Exception e){
            LOGGER.severe(String.format("An error occurred during the process. %s", e.getMessage()));
        }
    }
}
