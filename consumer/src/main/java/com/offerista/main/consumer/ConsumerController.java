package com.offerista.main.consumer;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class ConsumerController {

    private CSVFileWriter csvFileWriter;

    @Value("${application.variables.file_name}")
    private String fileName;

    @PostMapping("/api/v1/")
    public void post(@RequestBody List<Integer> numbers) throws IOException {
        List<Integer> primeNumbers = numbers.stream().filter(PrimeChecker::isPrime).collect(Collectors.toList());

        if(!primeNumbers.isEmpty()) {
            log.info("Prime numbers: {}", primeNumbers);
            CSVFileWriter.writeNumbersToCSV(primeNumbers.stream().map(String::valueOf).collect(Collectors.joining(",")),fileName);
        }

    }
}
