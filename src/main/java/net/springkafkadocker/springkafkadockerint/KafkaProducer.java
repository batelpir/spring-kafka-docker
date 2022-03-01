package net.springkafkadocker.springkafkadockerint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class KafkaProducer {
    @Value("${spring.kafka.consumer.topic}")
    private String TOPIC;

    @Autowired
    private KafkaTemplate<String,Stock> kafkaTemplate;

    public void sendMessage(List<Stock> stocks){
        for (Stock stock : stocks) {
            System.out.println("********** KafkaProducer - Stock Name: " + stock.getStockName());
            this.kafkaTemplate.send(TOPIC, stock);
        }
    }
}
