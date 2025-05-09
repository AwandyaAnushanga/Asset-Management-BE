package com.nexeyo.erp.NumberingSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NumberingSystemService {

    @Autowired
    private NumberingSystemRepo numberingSystemRepo;

    Logger logger = LoggerFactory.getLogger(NumberingSystemService.class);


    public ResponseEntity<?> addNumbering (NumberingSystem numberingSystem){

        Optional<NumberingSystem> numberingSystemOptional = numberingSystemRepo.findByTypeName(numberingSystem.getTypeName());

        if (numberingSystemOptional.isPresent()){
            logger.info("numbering system is present. Updating existing numbering system...");

            numberingSystemOptional.get().setId(numberingSystemOptional.get().getId());
            numberingSystemOptional.get().setLastNo(numberingSystem.getLastNo());
            numberingSystemOptional.get().setLastDocumentNo(numberingSystem.getLastDocumentNo());
            numberingSystemOptional.get().setType(numberingSystem.getType());
            numberingSystemOptional.get().setTypeName(numberingSystem.getTypeName());
            numberingSystemOptional.get().setStartCharacter(numberingSystem.getStartCharacter());

            logger.info("existing numbering system updated");
            return ResponseEntity.ok(numberingSystemRepo.save(numberingSystemOptional.get()));
        }

        logger.info("New numbering system added");
        return ResponseEntity.ok(numberingSystemRepo.save(numberingSystem));

    }

    public ResponseEntity<?> getById (int id){
        return ResponseEntity.ok(numberingSystemRepo.findById(id).orElseThrow(
                ()-> new RuntimeException("invalid Id")
        ));
    }

    public ResponseEntity<?> getAllWithPagination (int page, int pageSize, int order){
        return ResponseEntity.ok(numberingSystemRepo.findAll(PageRequest.of(page,pageSize, Sort.by(order == 0? Sort.Direction.ASC : Sort.Direction.DESC,"id"))));

    }

    public ResponseEntity<?> update (NumberingSystem numberingSystem){
        numberingSystemRepo.findById(numberingSystem.getId()).orElseThrow(
                ()-> new RuntimeException("Invalid id")
        );

        return ResponseEntity.ok(numberingSystemRepo.save(numberingSystem));

    }
}
