package weather.produser.sevice;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import weather.model.WeatherEvent;



@Service
public class WeatherProduser {
    private final Random random = new Random();

    @Autowired
    private KafkaTemplate<String, WeatherEvent> kafkaTemplate;
    
    private static final List<String> Cities = List.of(
    "Москва",
    "Санкт-Петербург",
    "Казань",
    "Новосибирск",
    "Екатеринбург",
    "Нижний Новгород",
    "Челябинск",
    "Самара",
    "Омск",
    "Ростов-на-Дону",
    "Уфа",
    "Красноярск",
    "Пермь",
    "Волгоград",
    "Владивосток",
    "Хабаровск",
    "Иркутск",
    "Якутск",
    "Магадан",
    "Чукотка"
    );

    
    @Scheduled(fixedRate = 5000)
    public void sendWeatherEvent(){
        WeatherEvent event = generateWeatherEvent();
        kafkaTemplate.send("weather-topic", event.getCity(), event);
        System.out.println("Отправленно " + event);
    }


    private WeatherEvent generateWeatherEvent(){
        return WeatherEvent.create(
            UUID.randomUUID().toString(),
            getRandomCity(),
            getRandomStatus(),
            getRandomTemperature());
            

    }
    


    private String getRandomCity(){
        return Cities.stream()
        .skip(random.nextInt(Cities.size())).findFirst()
        .orElseThrow();
    }

    private String getRandomStatus(){
        return Arrays.stream(Status.values())
        .skip(random.nextInt(Cities.size())).findFirst()
        .map(Status::name)
        .orElse("RAINY");
    }

    private double getRandomTemperature(){
        double minTemp = -10.0;
        double maxTemp = 30.0;
        return Math.round((minTemp + (maxTemp - minTemp) * random.nextDouble()) * 10) / 10.0;
    }
    


    
}
