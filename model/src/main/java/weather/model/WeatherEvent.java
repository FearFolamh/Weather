package weather.model;

import lombok.Data;

@Data
public class WeatherEvent {

	private String id;
	private String city;
	private String status;
	private double temperature;

	public static WeatherEvent create(String id, String city, String status, Double temp) {
        WeatherEvent event = new WeatherEvent();
        event.setId(id);
        event.setCity(city);
        event.setStatus(status);
        event.setTemperature(temp);
        return event;
    }

}
