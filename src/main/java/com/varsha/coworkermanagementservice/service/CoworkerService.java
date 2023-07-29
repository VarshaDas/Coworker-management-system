package com.varsha.coworkermanagementservice.service;

import com.varsha.coworkermanagementservice.entity.Coworker;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CoworkerService {

        List<Coworker> parseCSVAndSave(MultipartFile csvFile);
}


