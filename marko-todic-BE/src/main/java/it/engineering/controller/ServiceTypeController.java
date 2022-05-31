package it.engineering.controller;

import it.engineering.dto.ApiResponse;
import it.engineering.dto.ServiceTypeDto;
import it.engineering.service.ServiceTypeService;
import it.engineering.utils.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> uploadFile(@RequestParam("file")MultipartFile file) {
        String message = "";

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                return new ResponseEntity<>(serviceTypeService.save(file), HttpStatus.OK);
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, message);
                return new ResponseEntity<>(apiResponse, HttpStatus.EXPECTATION_FAILED);
            }
        }
        message = "Please upload a csv file!";
        ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, message);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
