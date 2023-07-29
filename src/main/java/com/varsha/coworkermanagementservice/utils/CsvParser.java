package com.varsha.coworkermanagementservice.utils;

import com.varsha.coworkermanagementservice.entity.Coworker;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {
    static Logger LOGGER = LoggerFactory.getLogger(CsvParser.class);

    public static List<Coworker> parseCSVToList(MultipartFile csvFile) {
        List<Coworker> coworkerList = new ArrayList<>();


        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        try (Reader reader = new InputStreamReader(csvFile.getInputStream());
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {

            for (CSVRecord record : csvParser) {
                String first_name = record.get("first_name");
                String last_name = record.get("last_name");
                String email = record.get("email");
                String gender = record.get("gender");
                String country = record.get("country");
                String postalCode = record.get("postal_code");
                String phone = record.get("phone_number");
                LocalDate start_date = LocalDate.parse(record.get("start_date"),dateFormatter);
                LocalDate end_date = LocalDate.parse(record.get("end_date"), dateFormatter);

                LOGGER.info("record.get(\"start_date\") "+record.get("start_date"));
                Coworker coworker = new Coworker();
                coworker.setName(first_name + " " + last_name);
                coworker.setGender(gender);
                coworker.setEmail(email);
                coworker.setCountry(country);
                coworker.setPostalCode(postalCode);
                coworker.setPhone(phone);
                coworker.setStartDate(start_date);
                coworker.setEndDate(end_date);

                coworkerList.add(coworker);
            }
        } catch (IOException e) {
            LOGGER.error("IOException occurred " + e);
        }

        return coworkerList;
    }
}
