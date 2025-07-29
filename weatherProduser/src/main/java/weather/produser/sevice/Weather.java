package weather.produser.sevice;

import lombok.Data;

@Data
public class Weather {
    private String city;
    private Status status;
    private Double temperature;

}
