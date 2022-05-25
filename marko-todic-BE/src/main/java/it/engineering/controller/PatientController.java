package it.engineering.controller;

import it.engineering.dto.ApiResponse;
import it.engineering.dto.PagedResponse;
import it.engineering.dto.PatientSimpleDto;
import it.engineering.service.PatientService;
import it.engineering.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public PagedResponse findAll(@RequestParam(value = "pageNumber", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
                                 @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                 @RequestParam(value = "sortField", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortField,
                                 @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){
        return patientService.findAll(pageNumber, pageSize, sortField, sortDir);
    }

    @PostMapping
    public ResponseEntity<PatientSimpleDto> save(@Valid @RequestBody PatientSimpleDto patientSimpleDto){
        return new ResponseEntity<>(patientService.save(patientSimpleDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientSimpleDto> update(@PathVariable("id") Integer id,
                                                   @Valid @RequestBody PatientSimpleDto patientSimpleDto){
        return new ResponseEntity<>(patientService.update(id, patientSimpleDto), HttpStatus.CREATED);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Integer id){
        return new ResponseEntity<>(patientService.delete(id), HttpStatus.OK);
    }
}