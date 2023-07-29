package com.varsha.coworkermanagementservice.utils;

import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CsvCleaner {

    public static InputStream cleanCsv(MultipartFile csvFile) throws IOException {
        ByteArrayOutputStream cleanedCsvContent = new ByteArrayOutputStream();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream()))) {
            // Read the header line
            String header = reader.readLine();
            // Write the header to the cleaned CSV content
            cleanedCsvContent.write(header.getBytes());
            cleanedCsvContent.write('\n');

            String line;
            while ((line = reader.readLine()) != null) {
                // Clean and process each line
                String cleanedLine = cleanAndProcessLine(line);
                // Write the cleaned line to the cleaned CSV content
                cleanedCsvContent.write(cleanedLine.getBytes());
                cleanedCsvContent.write('\n');
            }
        }

        return new ByteArrayInputStream(cleanedCsvContent.toByteArray());
    }


    public static String cleanAndProcessLine(String line) {
        // Split the CSV line by the comma (or any other delimiter used in the CSV)
        String[] values = line.split(",");

        // Clean and process each value
        for (int i = 0; i < values.length; i++) {
            String cleanedValue = cleanValue(values[i]);
            values[i] = cleanedValue;
        }

        // Join the cleaned values back into a single CSV line
        return Arrays.stream(values)
                .collect(Collectors.joining(","));
    }

    public static String cleanValue(String value) {
        // Implement your specific value cleaning logic here
        // For example, you may want to remove leading/trailing whitespace, handle missing values, replace invalid characters, etc.

        // Removing leading and trailing whitespace
        value = value.trim();

        // Handle missing values
        if (value.equals("N/A") || value.isEmpty()) {
            value = "Unknown";
        }

        // Replace invalid characters (e.g., double quotes)
        value = value.replace("\"", "");

        // Other specific cleaning operations as needed

        return value;
    }
}
