package com.offerista.main.producer;



import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ProducerService {

    private final PublisherService publisherService;

    private final List<Integer> numberStream = Collections.synchronizedList(new ArrayList<>());

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private CSVFileWriter csvFileWriter;

    @Value("${application.variables.file_name}")
    private String fileName;

    @Value("${application.variables.max_stream_size}")
    private int maxStreamSize;

    @Value("${application.variables.list_size}")
    private int listSize;

    public ProducerService(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostConstruct
    void postConstruct() throws IOException {
        generateAndProcessRandomNumbers();
    }

    private void processNumbers(List<Integer> numbers) {
        try {
            log.info("Sending numbers to consumer: {}", numbers);
            publisherService.publishNumber(numbers); // Send to Consumer
            String numbersToWrite = numbers.stream().map(String::valueOf).collect(Collectors.joining(","));
            CSVFileWriter.writeNumbersToCSV(numbersToWrite,fileName); // Write to CSV
        }  catch (IOException e) {
            throw new RuntimeException("Error while processing numbers", e);
        }
    }

    private void generateAndProcessRandomNumbers() throws IOException {

        scheduler.scheduleAtFixedRate(() -> {
            if (numberStream.size() < maxStreamSize) {
                List<Integer> numbers =  RandomNumberGenerator.generateRandomNumbers(listSize);
                processNumbers(numbers);
                numberStream.addAll(numbers);
            } else {
                scheduler.shutdown();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
}
