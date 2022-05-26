package it.engineering.controller;

import it.engineering.dto.ApiResponse;
import it.engineering.dto.ExaminationDto;
import it.engineering.dto.ExaminationFullDto;
import it.engineering.dto.PagedResponse;
import it.engineering.service.ExaminationService;
import it.engineering.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/examination")
public class ExaminationController {

    @Autowired
    private ExaminationService examinationService;

    @GetMapping
    public PagedResponse findAll(@RequestParam(value = "pageNumber", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
                                 @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                 @RequestParam(value = "sortField", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortField,
                                 @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){
        return examinationService.findAll(pageNumber, pageSize, sortField, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExaminationFullDto> findById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(examinationService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ExaminationDto> save(@Valid @RequestBody ExaminationDto examinationDto){
        return new ResponseEntity<>(examinationService.save(examinationDto), HttpStatus.CREATED);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Integer id){
        return new ResponseEntity<>(examinationService.delete(id), HttpStatus.OK);
    }
}
