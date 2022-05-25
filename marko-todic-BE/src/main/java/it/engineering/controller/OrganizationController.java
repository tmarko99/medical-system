package it.engineering.controller;

import it.engineering.dto.OrganizationDto;
import it.engineering.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {
    @Autowired
    private  OrganizationService organizationService;

    @GetMapping
    public ResponseEntity<List<OrganizationDto>> findAll(){
        return new ResponseEntity<>(organizationService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrganizationDto> save(@RequestBody OrganizationDto organizationDto){
        return new ResponseEntity<>(organizationService.save(organizationDto), HttpStatus.OK);
    }
}
