package com.nexeyo.erp.Supplier;

import com.nexeyo.erp.NumberingSystem.NumberingSystem;
import com.nexeyo.erp.NumberingSystem.NumberingSystemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepo supplierRepo;
    @Autowired
    private NumberingSystemRepo numberingSystemRepo;

    public ResponseEntity<?> Add (Supplier supplier){

        Optional<NumberingSystem> numberingSystem = numberingSystemRepo.findByTypeName("supplier");
        if (numberingSystem.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please set Numbering system, 'supplier' ");
        }

        try {
            Integer lastNo = numberingSystem.get().getLastNo();
            String charr = numberingSystem.get().getStartCharacter();
            for (int i = 0; i < 5; i++) {
                try {
                    lastNo++;
                    supplier.setSupplierNo(charr + lastNo);
                    supplier.setSupplierNoWithoutCharacters(lastNo);

                    numberingSystem.get().setLastNo(lastNo);
                    numberingSystem.get().setLastDocumentNo(supplier.getId());
                    numberingSystemRepo.save(numberingSystem.get());
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                    if (i >= 4) {
                        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Something went wrong !");
                    }
                }
            }
        } catch (Exception e) {

        }
        return ResponseEntity.ok(supplierRepo.save(supplier));
    }

    public ResponseEntity<?> GetALl (int page, int page_size, int order){
        return ResponseEntity.ok(supplierRepo.findAll(PageRequest.of(page,page_size, Sort.by(order == 0? Sort.Direction.ASC : Sort.Direction.DESC, "id"))));
    }

    public ResponseEntity<?> GetById (int id){
        return ResponseEntity.ok(supplierRepo.findById(id).orElseThrow(
                ()-> new RuntimeException("Invalid Id!!!")
        ));
    }
}
