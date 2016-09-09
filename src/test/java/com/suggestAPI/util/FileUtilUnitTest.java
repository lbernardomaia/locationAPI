package com.suggestAPI.util;

import com.suggestAPI.domain.City;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class FileUtilUnitTest {

    FileUtil fileUtil;

    public FileUtilUnitTest() {
        fileUtil = new FileUtil();
    }

    @Test
    public void export_to_csv_with_none_data() throws IOException, URISyntaxException {
        List<City> cities = new ArrayList<>();
        boolean isFileCreated = fileUtil.exportToCSV(cities);
        assert !isFileCreated;
    }
}