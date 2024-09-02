package com.example.computer.controller;

import com.example.computer.entity.Computer;
import com.example.computer.exception.ComputerNotFoundException;
import com.example.computer.service.ComputerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/pc")
public class ComputerController {

    @Autowired
    private ComputerService computerService;

    @PostMapping("/add")
    public ResponseEntity<Object> addComputer(@RequestBody Computer computer){
        if(computer==null){
            log.warn("Attempt to add null Object!");
            return ResponseEntity.badRequest().body("Computer Object cannot be null!");
        }

           Computer computerSaved = computerService.saveComputer(computer);
           return new ResponseEntity<>(computerSaved,HttpStatus.CREATED);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getComputerById(@PathVariable("id") Integer computerId){
        if(computerId==null){
            log.warn("ID is null!");
            return ResponseEntity.badRequest().body("Null computer ID is sent!");
        }
        try {
            Computer computerRetrieved = computerService.getComputerById(computerId);
            log.info("computer retrieved : {} ",computerRetrieved);
            return ResponseEntity.ok(computerRetrieved);
        } catch (ComputerNotFoundException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllComputers(){
        List<Computer> computerList = computerService.getAllComputers();
        if(computerList.isEmpty()){
            log.warn("No Computer found in database!");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No item exists");
        }
          return  ResponseEntity.ok(computerList);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> removeComputer(@PathVariable("id") Integer computerId){
        log.info("ID of computer to remove : ",computerId);
        try {
            log.info("Removing computer.....");
            computerService.removeComputer(computerId);
            log.info("Computer Removed!");
            return ResponseEntity.ok("computer removed successfully!");
        } catch (ComputerNotFoundException e) {
            log.error("Computer with this ID not found : ",computerId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (Exception e){
            log.error("Generic error : ",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


}
