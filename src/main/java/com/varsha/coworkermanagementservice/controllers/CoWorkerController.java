package com.varsha.coworkermanagementservice.controllers;


//import jakarta.servlet.http.HttpServletResponse;
//import org.bson.Document;
import com.varsha.coworkermanagementservice.entity.Coworker;
import com.varsha.coworkermanagementservice.repository.CoworkerRepository;
import io.swagger.models.Response;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class CoWorkerController {

    @Autowired
    CoworkerRepository coworkerRepository;

    @GetMapping("/get")
    public List<Coworker> getAllCoworkers(){
        return coworkerRepository.findAll();

    }

    @PostMapping("/add")
    public ResponseEntity addCoworker(@RequestBody Coworker coworker){
        coworkerRepository.save(coworker);
        return ResponseEntity.ok(coworker);
    }

    @ApiIgnore
    @RequestMapping(value = "/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");

    }


}
