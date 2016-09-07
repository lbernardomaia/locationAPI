package util;

public enum  EndpointEnum {
    SUGGEST("suggest");

    private String key;

    EndpointEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
