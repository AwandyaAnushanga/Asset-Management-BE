package com.nexeyo.erp.Technician;

import com.nexeyo.erp.AssetStatus.AssetStatus;
import com.nexeyo.erp.NumberingSystem.NumberingSystem;
import com.nexeyo.erp.NumberingSystem.NumberingSystemRepo;
import com.nexeyo.erp.jwt.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TechnicianService {

    @Autowired
    private TechnicianRepo technicianRepo;
    @Autowired
    private NumberingSystemRepo numberingSystemRepo;

    public ResponseEntity<?> Add (Technician technician){

        Optional<NumberingSystem> numberingSystem = numberingSystemRepo.findByTypeName("technician");
        if (numberingSystem.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please set Numbering system, 'technician' ");
        }

        try {
            Integer lastNo = numberingSystem.get().getLastNo();
            String charr = numberingSystem.get().getStartCharacter();
            for (int i = 0; i < 5; i++) {
                try {
                    lastNo++;
                    technician.setTechnicianNo(charr + lastNo);
                    technician.setTechnicianNoWithoutCharacter(lastNo);

                    numberingSystem.get().setLastNo(lastNo);
                    numberingSystem.get().setLastDocumentNo(technician.getId());
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

        return ResponseEntity.ok(technicianRepo.save(technician));
    }

    public ResponseEntity<?> GetById (int id){
        return ResponseEntity.ok(technicianRepo.findById(id).orElseThrow(
                ()-> new RuntimeException("Invalid Id!!!")
        ));
    }

    public ResponseEntity<?> GetAll (int page, int page_size, int order){
        return ResponseEntity.ok(technicianRepo.findAll(PageRequest.of(page,page_size, Sort.by(order == 0? Sort.Direction.ASC : Sort.Direction.DESC, "id"))));
    }

}
