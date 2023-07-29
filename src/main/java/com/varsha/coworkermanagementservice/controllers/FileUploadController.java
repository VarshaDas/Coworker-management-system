package com.varsha.coworkermanagementservice.controllers;

import com.varsha.coworkermanagementservice.entity.Coworker;
import com.varsha.coworkermanagementservice.repository.CoworkerRepository;
import com.varsha.coworkermanagementservice.service.CoworkerService;
import com.varsha.coworkermanagementservice.utils.CsvParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class FileUploadController {

    @Autowired
    CoworkerService coworkerService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCSVFile(@RequestPart("file") MultipartFile file) {
        // Check if the uploaded file is not empty and is of CSV format
        if (file.isEmpty() || !file.getOriginalFilename().endsWith(".csv")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload a valid CSV file.");
        }

        coworkerService.parseCSVAndSave(file);

        // Replace the below message with your custom response
        return ResponseEntity.ok("CSV file uploaded successfully.");
    }
}
