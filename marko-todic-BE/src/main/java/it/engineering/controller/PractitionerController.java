package it.engineering.controller;

import it.engineering.dto.ApiResponse;
import it.engineering.dto.PagedResponse;
import it.engineering.dto.PractitionerFullDto;
import it.engineering.dto.PractitionerSimpleDto;
import it.engineering.service.PractitionerService;
import it.engineering.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/practitioner")
public class PractitionerController {

    @Autowired
    private PractitionerService practitionerService;

    @GetMapping
    public PagedResponse findAll(@RequestParam(value = "pageNumber", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
                                 @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                 @RequestParam(value = "sortField", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortField,
                                 @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){
        return practitionerService.findAll(pageNumber, pageSize, sortField, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PractitionerFullDto> findById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(practitionerService.findById(id), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<PractitionerSimpleDto> save(@RequestBody PractitionerSimpleDto practitionerSimpleDto){
        return new ResponseEntity<>(practitionerService.save(practitionerSimpleDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PractitionerSimpleDto> update(@PathVariable("Id") Integer id,
                                                      @RequestBody PractitionerSimpleDto practitionerSimpleDto){
        return new ResponseEntity<>(practitionerService.update(id, practitionerSimpleDto), HttpStatus.CREATED);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Integer id){
        return new ResponseEntity<>(practitionerService.delete(id), HttpStatus.OK);
    }
}
