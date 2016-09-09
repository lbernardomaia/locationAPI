package com.suggestAPI.util;

import org.junit.Test;

public class PropertyUtilUnitTest {

    @Test
    public void test_with_resources(){
        PropertyUtil propertyUtil = new PropertyUtil();
        String valueRetrieved = propertyUtil.getValue(EndpointEnum.SUGGEST.getKey());

        String valueExpected = "http://api.goeuro.com/api/v2/position/suggest/en/";

        assert valueRetrieved.equals(valueExpected);

    }

}
