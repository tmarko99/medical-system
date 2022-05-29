package it.engineering.controller;

import it.engineering.dto.*;
import it.engineering.entity.OrganizationType;
import it.engineering.service.OrganizationService;
import it.engineering.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/organization")
public class OrganizationController {
    @Autowired
    private  OrganizationService organizationService;

    @GetMapping
    public PagedResponse findAll(@RequestParam(value = "filter", required = false) String filter,
                                 @RequestParam(value = "pageNumber", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
                                 @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                 @RequestParam(value = "sortField", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortField,
                                 @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){
        return organizationService.findAll(filter, pageNumber, pageSize, sortField, sortDir);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<OrganizationIdentifierNameDto>> findAllSimple(){
        return new ResponseEntity<>(organizationService.findAllSimple(), HttpStatus.OK);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<OrganizationFullDto> findByIdView(@PathVariable("id") Integer id){
        return new ResponseEntity<>(organizationService.findByIdView(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDto> findByIdSimple(@PathVariable("id") Integer id){
        return new ResponseEntity<>(organizationService.findByIdEdit(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrganizationDto> save(@Valid @RequestBody OrganizationDto organizationDto){
        return new ResponseEntity<>(organizationService.save(organizationDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizationDto> update(@PathVariable("id") Integer id, @Valid @RequestBody OrganizationDto organizationDto){
        return new ResponseEntity<>(organizationService.update(id, organizationDto), HttpStatus.CREATED);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Integer id){
        return new ResponseEntity<>(organizationService.delete(id), HttpStatus.OK);
    }
}
