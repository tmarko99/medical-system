package it.engineering.controller;

import it.engineering.dto.ServiceTypeDto;
import it.engineering.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/serviceType")
public class ServiceTypeController {

    @Autowired
    private ServiceTypeService serviceTypeService;

    @GetMapping
    public ResponseEntity<List<ServiceTypeDto>> findAll(){
        return new ResponseEntity<>(serviceTypeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceTypeDto> findById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(serviceTypeService.findById(id), HttpStatus.OK);
    }
}
