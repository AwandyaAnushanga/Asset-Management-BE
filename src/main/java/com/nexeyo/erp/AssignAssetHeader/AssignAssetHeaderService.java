package com.nexeyo.erp.AssignAssetHeader;

import com.nexeyo.erp.AssignAssetLine.AssignAssetLine;
import com.nexeyo.erp.AssignAssetLine.AssignAssetLineRepo;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssignAssetHeaderService {

    @Autowired
    private AssignAssetHeaderRepo assignAssetHeaderRepo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AssignAssetLineRepo assignAssetLineRepo;

    public ResponseEntity<?> Add (AssignAssetHeader assignAssetHeader){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please send valid token for user ");
        }

        assignAssetHeader.setCreatedBy(userOptional.get().getId());
        assignAssetHeader.setCreateAt(LocalDateTime.now());
        AssignAssetHeader saved = assignAssetHeaderRepo.save(assignAssetHeader);

        AssignAssetLine line = new AssignAssetLine();
        line.setHeader_id(saved.getId());
        line.setCreateAt(saved.getCreateAt());
        line.setCreatedBy(saved.getCreatedBy());
        line.setAssignedUser(saved.getAssignedUser());
        assignAssetLineRepo.save(line);

        return ResponseEntity.ok(saved);
    }

    public ResponseEntity<?> GetAll (int page, int page_size, int order){
        return ResponseEntity.ok(assignAssetHeaderRepo.findAll(PageRequest.of(page,page_size, Sort.by(order==0? Sort.Direction.ASC : Sort.Direction.DESC, "id"))));
    }

    public ResponseEntity<?> GetById (int id){
        return ResponseEntity.ok(assignAssetHeaderRepo.findById(id).orElseThrow(
                ()-> new RuntimeException("Invalid id")
        ));
    }
}
