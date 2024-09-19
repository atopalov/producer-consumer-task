package com.offerista.main.consumer;

import java.io.FileWriter;
import java.io.IOException;

public class CSVFileWriter {

    public static void writeNumbersToCSV(String numbers,String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath,true)) {
            writer.append(numbers).append(", \n");
            writer.flush();
        }
}


}
