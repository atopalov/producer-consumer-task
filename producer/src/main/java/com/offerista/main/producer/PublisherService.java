package com.offerista.main.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PublisherService {

    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${application.variables.consumer_url}")
    private String consumerUrl;

    public void publishNumber(List<Integer> numbers) {
        restTemplate.postForEntity(consumerUrl, numbers, Void.class);
    }
}