package com.nexeyo.erp.AssetImpairment;

import com.nexeyo.erp.AssetLine.AssetLine;
import com.nexeyo.erp.AssetLine.AssetLineRepo;
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
public class AssetImpairmentService {

    @Autowired
    private AssetImpairmentRepo assetImpairmentRepo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AssetLineRepo assetLineRepo;

    public ResponseEntity<?> Add (AssetImpairment assetImpairment){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please send valid token for user ");
        }

        Optional<AssetLine> assetLineOptional = assetLineRepo.findById(assetImpairment.getAsset_line_id());
        if (assetLineOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid asset line id!!!");
        }

        double impairment = assetImpairment.getImpairment();
        double newValue = assetLineOptional.get().getUpdatedValue() - impairment;

        assetImpairment.setCreateAt(LocalDateTime.now());
        assetImpairment.setCreateBy(userOptional.get().getId());
        assetImpairment.setApproved(false);
        AssetImpairment savedImpairment = assetImpairmentRepo.save(assetImpairment);

        assetLineOptional.get().setUpdatedValue(newValue);
        assetLineRepo.save(assetLineOptional.get());

        return ResponseEntity.ok(savedImpairment);
    }

    public ResponseEntity<?> Approve (int id ){

        Optional<AssetImpairment> assetImpairmentOptional = assetImpairmentRepo.findById(id);
        if (assetImpairmentOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Asset Impairment");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please send valid token for user ");
        }

        if(assetImpairmentOptional.get().getApproved()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already Approved");
        }

        assetImpairmentOptional.get().setApproved(true);
        assetImpairmentOptional.get().setApprovedAt(LocalDateTime.now());
        assetImpairmentOptional.get().setApprovedBy(userOptional.get().getId());
        return ResponseEntity.ok(assetImpairmentRepo.save(assetImpairmentOptional.get()));
    }

    public ResponseEntity<?> GetAll (int page, int page_size, int order){
        return ResponseEntity.ok(assetImpairmentRepo.findAll(PageRequest.of(page,page_size, Sort.by(order == 0? Sort.Direction.ASC : Sort.Direction.DESC, "id"))));
    }

    public ResponseEntity<?> GetById (int id){
        return ResponseEntity.ok(assetImpairmentRepo.findById(id).orElseThrow(
                ()-> new RuntimeException("Invalid Id!!!")
        ));
    }
}
