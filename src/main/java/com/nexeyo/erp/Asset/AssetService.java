package com.nexeyo.erp.Asset;

import com.nexeyo.erp.AssetStatus.AssetStatus;
import com.nexeyo.erp.AssetStatus.AssetStatusRepo;
import com.nexeyo.erp.NumberingSystem.NumberingSystem;
import com.nexeyo.erp.NumberingSystem.NumberingSystemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AssetService {

    @Autowired
    private AssetRepo assetRepo;
    @Autowired
    private NumberingSystemRepo numberingSystemRepo;
    @Autowired
    private AssetStatusRepo assetStatusRepo;

    public ResponseEntity<?> Add (Asset asset){

        Optional<NumberingSystem> numberingSystem = numberingSystemRepo.findByTypeName("asset");
        if (numberingSystem.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please set Numbering system, 'asset' ");
        }

        Optional<AssetStatus> assetStatusOptional = assetStatusRepo.findByName("active");
        if (assetStatusOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid status");
        }

        try {
            Integer lastNo = numberingSystem.get().getLastNo();
            String charr = numberingSystem.get().getStartCharacter();
            for (int i = 0; i < 5; i++) {
                try {
                    lastNo++;
                    asset.setAssertNo(charr + lastNo);
                    asset.setAssertNoWithoutCharacters(lastNo);

                    numberingSystem.get().setLastNo(lastNo);
                    numberingSystem.get().setLastDocumentNo(asset.getId());
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
        asset.setCreateAt(LocalDateTime.now());
        asset.setDisposed(false);
        asset.setStatus(assetStatusOptional.get().getId());
        return ResponseEntity.ok(assetRepo.save(asset));
    }

    public ResponseEntity<?> GetAll (int page, int page_size, int order){
        return ResponseEntity.ok(assetRepo.findAll(PageRequest.of(page,page_size, Sort.by(order == 0? Sort.Direction.ASC : Sort.Direction.DESC, "id"))));

    }

    public ResponseEntity<?> GetById (int id){
        return ResponseEntity.ok(assetRepo.findById(id).orElseThrow(
                ()-> new RuntimeException("Invalid Id!!!")
        ));
    }

}
