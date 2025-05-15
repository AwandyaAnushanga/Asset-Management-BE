package com.nexeyo.erp.AssetHeader;

import com.nexeyo.erp.AssetLine.AssetLine;
import com.nexeyo.erp.AssetLine.AssetLineRepo;
import com.nexeyo.erp.AssetStatus.AssetStatus;
import com.nexeyo.erp.AssetStatus.AssetStatusRepo;
import com.nexeyo.erp.NumberingSystem.NumberingSystem;
import com.nexeyo.erp.NumberingSystem.NumberingSystemRepo;
import com.nexeyo.erp.jwt.models.User;
import com.nexeyo.erp.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AssetHeaderService {

    @Autowired
    private AssetHeaderRepo assetHeaderRepo;
    @Autowired
    private NumberingSystemRepo numberingSystemRepo;
    @Autowired
    private AssetStatusRepo assetStatusRepo;
    @Autowired
    private AssetLineRepo assetLineRepo;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> Add (AssetHeader assetHeader){

        Optional<NumberingSystem> numberingSystem = numberingSystemRepo.findByTypeName("asset");
        if (numberingSystem.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please set Numbering system, 'asset' ");
        }

        Optional<AssetStatus> assetStatusOptional = assetStatusRepo.findByName("active");
        if (assetStatusOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid status");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please send valid token for user ");
        }

        try {
            Integer lastNo = numberingSystem.get().getLastNo();
            String charr = numberingSystem.get().getStartCharacter();
            for (int i = 0; i < 5; i++) {
                try {
                    lastNo++;
                    assetHeader.setAssertNo(charr + lastNo);
                    assetHeader.setAssertNoWithoutCharacters(lastNo);

                    numberingSystem.get().setLastNo(lastNo);
                    numberingSystem.get().setLastDocumentNo(assetHeader.getId());
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

        double totalValue = assetHeader.getInitialValue() * assetHeader.getQty();
        assetHeader.setTotalValue(totalValue);
        assetHeader.setCreateAt(LocalDateTime.now());
        assetHeader.setCreateBy(userOptional.get().getId());
        assetHeader.setDisposed(false);
        assetHeader.setStatus(assetStatusOptional.get().getId());
        assetHeader.setApproved(false);
        AssetHeader savedHeader = assetHeaderRepo.save(assetHeader);

        AssetLine assetLine = new AssetLine();
        assetLine.setHeader_id(savedHeader.getId());
        assetLine.setAssertNo(savedHeader.getAssertNo());
        assetLine.setAssertNoWithoutCharacters(savedHeader.getAssertNoWithoutCharacters());
        assetLine.setCustodian_id(savedHeader.getCustodian_id());
        assetLine.setCreateAt(savedHeader.getCreateAt());
        assetLine.setCreateBy(savedHeader.getCreateBy());
        assetLine.setApproved(savedHeader.getApproved());
//        assetLine.setUpdateReason(savedHeader.getUpdateReason());
//        assetLine.setUpdateAt(savedHeader.getUpdateAt());
//        assetLine.setUpdateBy(savedHeader.getUpdateBy());
        assetLineRepo.save(assetLine);

        return ResponseEntity.ok(savedHeader);
    }

    public ResponseEntity<?> GetAll (int page, int page_size, int order){
        return ResponseEntity.ok(assetHeaderRepo.findAll(PageRequest.of(page,page_size, Sort.by(order == 0? Sort.Direction.ASC : Sort.Direction.DESC, "id"))));

    }

    public ResponseEntity<?> GetById (int id){
        return ResponseEntity.ok(assetHeaderRepo.findById(id).orElseThrow(
                ()-> new RuntimeException("Invalid Id!!!")
        ));
    }

    public ResponseEntity<?> Update (AssetHeader assetHeader){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please send valid token for user ");
        }

        Optional<AssetHeader> assetHeaderOptional = assetHeaderRepo.findById(assetHeader.getId());
        if (assetHeaderOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid header!!!");
        }

        Optional<AssetStatus> assetStatusOptional = assetStatusRepo.findByName("active");
        if (assetStatusOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid status");
        }

        assetHeader.setUpdateAt(LocalDateTime.now());
        assetHeader.setUpdateBy(userOptional.get().getId());
        assetHeader.setApproved(false);
        AssetHeader savedHeader = assetHeaderRepo.save(assetHeader);

        AssetLine line = new AssetLine();
        line.setUpdateAt(savedHeader.getUpdateAt());
        line.setUpdateBy(savedHeader.getUpdateBy());
        line.setUpdateReason(savedHeader.getUpdateReason());
        line.setAssertNo(savedHeader.getAssertNo());
        line.setAssertNoWithoutCharacters(savedHeader.getAssertNoWithoutCharacters());
        line.setApproved(false);
        assetLineRepo.save(line);

        return ResponseEntity.ok(savedHeader);
    }

    public ResponseEntity<?> updateValue (AssetHeader assetHeader){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please send valid token for user ");
        }

        Optional<AssetHeader> assetHeaderOptional = assetHeaderRepo.findById(assetHeader.getId());
        if (assetHeaderOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid header!!!");
        }

        double updatedValue = assetHeader.getUpdatedValue();
        double totalValue = updatedValue * assetHeader.getQty();

        assetHeader.setUpdateAt(LocalDateTime.now());
        assetHeader.setUpdateBy(userOptional.get().getId());
        assetHeader.setTotalValue(totalValue);
        assetHeader.setApproved(false);

        AssetHeader savedHeader = assetHeaderRepo.save(assetHeader);

        AssetLine line = new AssetLine();
        line.setUpdateAt(savedHeader.getUpdateAt());
        line.setUpdateBy(savedHeader.getUpdateBy());
        line.setUpdateReason(savedHeader.getUpdateReason());
        line.setAssertNo(savedHeader.getAssertNo());
        line.setAssertNoWithoutCharacters(savedHeader.getAssertNoWithoutCharacters());
        line.setApproved(false);
        assetLineRepo.save(line);

        return ResponseEntity.ok(savedHeader);
    }

    public ResponseEntity<?> Approve (int id){

        Optional<AssetHeader> assetHeaderOptional = assetHeaderRepo.findById(id);
        if (assetHeaderOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Asset Id");
        }

        Optional<AssetLine> assetLineOptional = assetLineRepo.findById(assetHeaderOptional.get().getAssetLine().getId());
        if (assetLineOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid AssetLine Id");
        }

        assetHeaderOptional.get().setApproved(true);
        AssetHeader assetHeader = assetHeaderRepo.save(assetHeaderOptional.get());

        assetLineOptional.get().setApproved(true);
        assetLineRepo.save(assetLineOptional.get());

        return ResponseEntity.ok(assetHeader);

    }

}
