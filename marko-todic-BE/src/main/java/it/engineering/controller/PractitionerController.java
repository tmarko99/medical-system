package it.engineering.controller;

import it.engineering.dto.*;
import it.engineering.service.PractitionerService;
import it.engineering.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/practitioner")
public class PractitionerController {

    @Autowired
    private PractitionerService practitionerService;

    @GetMapping
    public PagedResponse findAll(@RequestParam(value = "filter", required = false) String filter,
                                 @RequestParam(value = "pageNumber", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
                                 @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                 @RequestParam(value = "sortField", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortField,
                                 @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){
        return practitionerService.findAll(filter, pageNumber, pageSize, sortField, sortDir);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<PractitionerIdentifierNameDto>> findAll(){
        return new ResponseEntity<>(practitionerService.findAllSimple(), HttpStatus.OK);
    }


    @GetMapping("/findByOrganization/{id}")
    public ResponseEntity<List<PractitionerIdentifierNameDto>> findAll(@PathVariable("id") Integer id){
        return new ResponseEntity<>(practitionerService.findByOrganization(id), HttpStatus.OK);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<PractitionerFullDto> findByIdView(@PathVariable("id") Integer id){
        return new ResponseEntity<>(practitionerService.findByIdView(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PractitionerSimpleDto> findById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(practitionerService.findByIdSimple(id), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<PractitionerSimpleDto> save(@Valid @RequestBody PractitionerSimpleDto practitionerSimpleDto){
        return new ResponseEntity<>(practitionerService.save(practitionerSimpleDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PractitionerSimpleDto> update(@PathVariable("id") Integer id,
                                                        @Valid @RequestBody PractitionerSimpleDto practitionerSimpleDto) {
        return new ResponseEntity<>(practitionerService.update(id, practitionerSimpleDto), HttpStatus.CREATED);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Integer id){
        return new ResponseEntity<>(practitionerService.delete(id), HttpStatus.OK);
    }
}
