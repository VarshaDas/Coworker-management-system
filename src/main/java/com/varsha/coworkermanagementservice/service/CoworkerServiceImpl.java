package com.varsha.coworkermanagementservice.service;

import com.varsha.coworkermanagementservice.entity.Coworker;
import com.varsha.coworkermanagementservice.repository.CoworkerRepository;
import com.varsha.coworkermanagementservice.utils.CsvParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service

public class CoworkerServiceImpl implements CoworkerService{

    @Autowired
    CoworkerRepository coworkerRepository;
    @Override
    public List<Coworker> parseCSVAndSave(MultipartFile csvFile) {
        List<Coworker> coworkerList = CsvParser.parseCSVToList(csvFile);
        return coworkerRepository.saveAll(coworkerList);
    }
}
