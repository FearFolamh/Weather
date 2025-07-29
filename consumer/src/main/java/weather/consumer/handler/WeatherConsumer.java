package weather.consumer.handler;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import weather.model.WeatherEvent;

@Component
@KafkaListener(topics = "weather-topic")
public class WeatherConsumer {
    
    @KafkaHandler
    
    public void handle(WeatherEvent event){
        System.out.println("Получено событие погоды: " + event);
    }


}
