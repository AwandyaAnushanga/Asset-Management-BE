package com.nexeyo.erp.AssetTransfer;

import com.nexeyo.erp.AssetHeader.AssetHeader;
import com.nexeyo.erp.AssetHeader.AssetHeaderRepo;
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
public class AssetTransferService {

    @Autowired
    private AssetTransferRepo assetTransferRepo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AssetHeaderRepo assetHeaderRepo;
    @Autowired
    private AssetLineRepo assetLineRepo;

    public ResponseEntity<?> Add (AssetTransfer assetTransfer){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please send valid token for user ");
        }

        Optional<AssetHeader> assetHeaderOptional = assetHeaderRepo.findById(assetTransfer.getAssetLine().getHeader_id());
        if (assetHeaderOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid asset header!!!");
        }

        Optional<AssetLine> assetLineOptional = assetLineRepo.findById(assetTransfer.getAsset_line_id());
        if (assetLineOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid asset header!!!");
        }

        int transferQty = assetTransfer.getQty();
        if (assetHeaderOptional.get().getQty() < transferQty){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No enough quantity to transfer! Only left " + assetHeaderOptional.get().getQty());
        }

        assetHeaderOptional.get().setQty(assetHeaderOptional.get().getQty() - transferQty);
        assetHeaderOptional.get().setLocation_id(assetTransfer.getToLocation());
        assetHeaderRepo.save(assetHeaderOptional.get());

        assetLineOptional.get().setLocation_id(assetTransfer.getToLocation());
        assetLineRepo.save(assetLineOptional.get());

        assetTransfer.setCreateBy(userOptional.get().getId());
        assetTransfer.setCreateAt(LocalDateTime.now());
        assetTransfer.setApproved(false);
        return ResponseEntity.ok(assetTransferRepo.save(assetTransfer));

    }

    public ResponseEntity<?> GetAll (int page, int page_size, int order){
        return ResponseEntity.ok(assetTransferRepo.findAll(PageRequest.of(page,page_size, Sort.by(order == 0? Sort.Direction.ASC : Sort.Direction.DESC, "id"))));
    }

    public ResponseEntity<?> GetById (int id){
        return ResponseEntity.ok(assetTransferRepo.findById(id));
    }

    public ResponseEntity<?> Approve (int id){

        Optional<AssetTransfer> optionalAssetTransfer = assetTransferRepo.findById(id);
        if (optionalAssetTransfer.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Asset Transfer");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please send valid token for user ");
        }

        if(optionalAssetTransfer.get().getApproved()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already Approved");
        }

        optionalAssetTransfer.get().setApprovedAt(LocalDateTime.now());
        optionalAssetTransfer.get().setApprovedBy(userOptional.get().getId());
        optionalAssetTransfer.get().setApproved(true);
        return ResponseEntity.ok(assetTransferRepo.save(optionalAssetTransfer.get()));
    }
}
