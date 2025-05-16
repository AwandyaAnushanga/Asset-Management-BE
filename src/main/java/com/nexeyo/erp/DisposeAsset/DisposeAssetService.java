package com.nexeyo.erp.DisposeAsset;

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
public class DisposeAssetService {

    @Autowired
    private DisposeAssetRepo disposeAssetRepo;
    @Autowired
    private AssetLineRepo assetLineRepo;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> Add (DisposeAsset disposeAsset){

        Optional<AssetLine> assetLineOptional = assetLineRepo.findById(disposeAsset.getAsset_line_id());
        if (assetLineOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Asset Line id");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please send valid token for user ");
        }

        double initialValue = assetLineOptional.get().getInitialValue();
        double gainPrice = 0.0;
        double lossPrice = 0.0;

        if (initialValue < disposeAsset.getSalePrice()){
            gainPrice = disposeAsset.getSalePrice() - initialValue;
            disposeAsset.setGainValue(gainPrice);
            disposeAsset.setLossValue(0.0);
            disposeAsset.setGain(true);
            disposeAsset.setLoss(false);
        } else {
            lossPrice = initialValue - disposeAsset.getSalePrice();
            disposeAsset.setLossValue(lossPrice);
            disposeAsset.setGainValue(0.0);
            disposeAsset.setGain(false);
            disposeAsset.setLoss(true);
        }

        disposeAsset.setCreateAt(LocalDateTime.now());
        disposeAsset.setCreateBy(userOptional.get().getId());
        DisposeAsset savedDispose = disposeAssetRepo.save(disposeAsset);

        return ResponseEntity.ok(savedDispose);
    }

    public ResponseEntity<?> Approve (int id){

        Optional<DisposeAsset> optionalDisposeAsset = disposeAssetRepo.findById(id);
        if (optionalDisposeAsset.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid dispose asset id");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please send valid token for user ");
        }

        if(optionalDisposeAsset.get().getApproved()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already Approved");
        }

        Optional<AssetLine> assetLineOptional = assetLineRepo.findById(optionalDisposeAsset.get().getAsset_line_id());
        if (assetLineOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid asset line id");
        }

        optionalDisposeAsset.get().setApproved(true);
        optionalDisposeAsset.get().setApprovedBy(userOptional.get().getId());
        optionalDisposeAsset.get().setApprovedAt(LocalDateTime.now());
        DisposeAsset saved = disposeAssetRepo.save(optionalDisposeAsset.get());

        assetLineOptional.get().setDisposed(true);
        assetLineOptional.get().setDisposedBy(saved.getCreateBy());
        assetLineOptional.get().setDisposedDate(saved.getCreateAt());
        assetLineRepo.save(assetLineOptional.get());

        return ResponseEntity.ok(saved);
    }

    public ResponseEntity<?> GetALl (int page, int page_size, int order){
        return ResponseEntity.ok(disposeAssetRepo.findAll(PageRequest.of(page, page_size, Sort.by(order == 0? Sort.Direction.ASC : Sort.Direction.DESC))));
    }

    public ResponseEntity<?> GetById (int id){
        return ResponseEntity.ok(disposeAssetRepo.findById(id).orElseThrow(
                ()-> new RuntimeException("Invalid id!!!")
        ));
    }

}
