package it.engineering.controller;

import it.engineering.dto.*;
import it.engineering.service.ExaminationService;
import it.engineering.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/examination")
public class ExaminationController {

    @Autowired
    private ExaminationService examinationService;

    @PostMapping("/filter")
    public PagedResponse findAll(@RequestBody FilterDto filterDto,
                                 @RequestParam(value = "pageNumber", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
                                 @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                 @RequestParam(value = "sortField", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortField,
                                 @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){
        return examinationService.findAll(filterDto, pageNumber, pageSize, sortField, sortDir);
    }


    @GetMapping("/view/{id}")
    public ResponseEntity<ExaminationFullDto> findByIdView(@PathVariable("id") Integer id){
        return new ResponseEntity<>(examinationService.findByIdView(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExaminationDto> findById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(examinationService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ExaminationDto> save(@Valid @RequestBody ExaminationDto examinationDto){
        return new ResponseEntity<>(examinationService.save(examinationDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExaminationDto> update(@PathVariable("id") Integer id,
                                                 @Valid @RequestBody ExaminationDto examinationDto){
        return new ResponseEntity<>(examinationService.update(id, examinationDto), HttpStatus.CREATED);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Integer id){
        return new ResponseEntity<>(examinationService.delete(id), HttpStatus.OK);
    }
}
