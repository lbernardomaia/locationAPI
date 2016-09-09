package com.suggestAPI.util;

import com.suggestAPI.SuggestAPI;
import com.suggestAPI.domain.City;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.CodeSource;
import java.util.List;

public class FileUtil {
    public static final String CSV_NAME = "suggestCities.csv";

    private static String getProgramPath() throws IOException, URISyntaxException {
        CodeSource codeSource = SuggestAPI.class.getProtectionDomain().getCodeSource();
        File jarFile = new File(codeSource.getLocation().toURI().getPath());

        return jarFile.getParentFile().getPath();
    }

    public boolean exportToCSV(List<City> cities) throws IOException, URISyntaxException {
        if (isEmptySuggestCities(cities)){
            return false;
        }

        Path pathSuggestCities = Paths.get(getProgramPath(), CSV_NAME);

        Files.deleteIfExists(pathSuggestCities);

        Files.createFile(pathSuggestCities);

        writeFile(cities, pathSuggestCities);

        return true;
    }

    private void writeFile(List<City> cities, Path pathSuggestCities) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(pathSuggestCities,
                Charset.forName("UTF-8"),
                StandardOpenOption.APPEND)){

            for (City city : cities){
                writer.write(city.toString() + "\n");
            }
        }
    }

    private boolean isEmptySuggestCities(List<City> cities) {
        return cities == null || cities.isEmpty();
    }

}
