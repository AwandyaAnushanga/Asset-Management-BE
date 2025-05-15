package com.nexeyo.erp.AssetMaintenance;

import com.nexeyo.erp.AssetLine.AssetLine;
import com.nexeyo.erp.AssetLine.AssetLineRepo;
import com.nexeyo.erp.Technician.Technician;
import com.nexeyo.erp.Technician.TechnicianRepo;
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
import java.util.List;
import java.util.Optional;

@Service
public class AssetMaintenanceService {

    @Autowired
    private AssetMaintenanceRepo assetMaintenanceRepo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AssetLineRepo assetLineRepo;
    @Autowired
    private TechnicianRepo technicianRepo;

    public ResponseEntity<?> Add(List<AssetMaintenance> assetMaintenanceList) {

        for (int i=0; i<assetMaintenanceList.size(); i++){

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            Optional<User> userOptional = userRepository.findByUsername(username);
            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please send valid token for user ");
            }

            assetMaintenanceList.get(i).setCreateAt(LocalDateTime.now());
            assetMaintenanceList.get(i).setCreateBy(userOptional.get().getId());
        }

        return ResponseEntity.ok(assetMaintenanceRepo.saveAll(assetMaintenanceList));
    }

    public ResponseEntity<?> GetAll (int page, int page_size, int order){
        return ResponseEntity.ok(assetMaintenanceRepo.findAll(PageRequest.of(page,page_size, Sort.by(order==0? Sort.Direction.ASC : Sort.Direction.DESC,"id"))));
    }

    public ResponseEntity<?> GetById (int id){
        return ResponseEntity.ok(assetMaintenanceRepo.findById(id).orElseThrow(
                ()-> new RuntimeException("Invalid id!!!")
        ));
    }

    public ResponseEntity<?> AddMaintenanceCost (int id, double cost){

        Optional<AssetMaintenance> optionalAssetMaintenance = assetMaintenanceRepo.findById(id);
        if (optionalAssetMaintenance.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Asset Maintenance ID!!!");
        }

        Optional<AssetLine> assetLineOptional = assetLineRepo.findById(optionalAssetMaintenance.get().getAsset_line_id());
        if (assetLineOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid assetLine Id");
        }

        Optional<Technician> optionalTechnician = technicianRepo.findById(optionalAssetMaintenance.get().getTechnician_id());
        if (optionalTechnician.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("invalid technician id");
        }
        double newCost =  optionalAssetMaintenance.get().getCost() + cost;
        optionalAssetMaintenance.get().setCost(newCost);

        assetLineOptional.get().setMaintenanceCost(newCost);
        assetLineRepo.save(assetLineOptional.get());

        optionalTechnician.get().setTotalServiceCost(newCost);
        technicianRepo.save(optionalTechnician.get());

        return ResponseEntity.ok(assetMaintenanceRepo.save(optionalAssetMaintenance.get()));

    }
}
