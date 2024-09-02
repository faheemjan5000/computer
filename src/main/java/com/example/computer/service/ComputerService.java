package com.example.computer.service;

import com.example.computer.entity.Computer;
import com.example.computer.exception.ComputerNotFoundException;
import com.example.computer.repository.ComputerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ComputerService {

    @Autowired
    private ComputerRepository computerRepository;

    public Computer saveComputer(Computer computer){
     log.info("ComputerService.saveComputer method is called......");
     log.info("Computer to add : {} ",computer);
     return computerRepository.save(computer);
    }

    public Computer getComputerById(Integer computerId) throws ComputerNotFoundException {
        log.info("ComputerService.getComputerById() method is called......");
        Optional<Computer> optionalComputer = computerRepository.findById(computerId);
        if(optionalComputer.isPresent()){
            log.info("computer found : {}",optionalComputer.get());
            return optionalComputer.get();
        }else {
            log.error("computer not found with ID : ",computerId);
            throw new ComputerNotFoundException("computer not found");
        }
    }

    public List<Computer> getAllComputers(){
        log.info("ComputerService.getAllComputers() method is called......");
        return computerRepository.findAll();
    }

    public void removeComputer(Integer computerId) throws ComputerNotFoundException {
        log.info("ComputerService.removeComputer() method is called......");
        Computer computerRetrieved = getComputerById(computerId);
        if(computerRetrieved!=null){
            log.info("computer exists with ID : ",computerId);
            computerRepository.delete(computerRetrieved);
            log.info("computer removed!");
        }
    }
}
