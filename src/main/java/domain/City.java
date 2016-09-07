package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class City {

    @JsonProperty("_id")
    private long id;
    private String name;
    private String type;
    @JsonProperty("geo_position")
    private GeoPositon geoPositon;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GeoPositon getGeoPositon() {
        return geoPositon;
    }

    public void setGeoPositon(GeoPositon geoPositon) {
        this.geoPositon = geoPositon;
    }

    public String toString(){
        return id + ", " +
               name + ", " +
               type + ", " +
               geoPositon.getLatitude() + ", " +
               geoPositon.getLongitude();
    }
}
