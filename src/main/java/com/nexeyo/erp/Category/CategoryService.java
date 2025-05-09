package com.nexeyo.erp.Category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    Logger logger = LoggerFactory.getLogger(CategoryService.class);

    public ResponseEntity<?> Add (Category category){
        return ResponseEntity.ok(categoryRepo.save(category));
    }

    public ResponseEntity<?> GetByLevelCode(int levelCode, int category_id) {

        if (levelCode == 0) {
            return ResponseEntity.ok(categoryRepo.findByLevel_codeOrderByIdDesc(levelCode));
        } else if (levelCode == 1) {
            return ResponseEntity.ok(categoryRepo.findByLevel_codeAndLevel1OrderByIdDesc(levelCode, category_id));
        } else if (levelCode == 2) {
            return ResponseEntity.ok(categoryRepo.findByLevel_codeAndLevel2OrderByIdDesc(levelCode, category_id));
        } else if (levelCode == 3) {
            return ResponseEntity.ok(categoryRepo.findByLevel_codeAndLevel3OrderByIdDesc(levelCode, category_id));
        } else if (levelCode == 4) {
            return ResponseEntity.ok(categoryRepo.findByLevel_codeAndLevel4OrderByIdDesc(levelCode, category_id));
        } else if (levelCode == 5) {
            return ResponseEntity.ok(categoryRepo.findByLevel_codeAndLevel5OrderByIdDesc(levelCode, category_id));
        } else if (levelCode == 6) {
            return ResponseEntity.ok(categoryRepo.findByLevel_codeAndLevel6OrderByIdDesc(levelCode, category_id));
        } else if (levelCode == 7) {
            return ResponseEntity.ok(categoryRepo.findByLevel_codeAndLevel7OrderByIdDesc(levelCode, category_id));
        } else if (levelCode == 8) {
            return ResponseEntity.ok(categoryRepo.findByLevel_codeAndLevel8OrderByIdDesc(levelCode, category_id));
        } else if (levelCode == 9) {
            return ResponseEntity.ok(categoryRepo.findByLevel_codeAndLevel9OrderByIdDesc(levelCode, category_id));
        } else if (levelCode == 10) {
            return ResponseEntity.ok(categoryRepo.findByLevel_codeAndLevel10OrderByIdDesc(levelCode, category_id));
        } else if (levelCode == 11) {
            return ResponseEntity.ok(categoryRepo.findByLevel_codeAndLevel11OrderByIdDesc(levelCode, category_id));
        } else if (levelCode == 12) {
            return ResponseEntity.ok(categoryRepo.findByLevel_codeAndLevel12OrderByIdDesc(levelCode, category_id));
        } else if (levelCode == 13) {
            return ResponseEntity.ok(categoryRepo.findByLevel_codeAndLevel13OrderByIdDesc(levelCode, category_id));
        } else if (levelCode == 14) {
            return ResponseEntity.ok(categoryRepo.findByLevel_codeAndLevel14OrderByIdDesc(levelCode, category_id));
        } else if (levelCode == 15) {
            return ResponseEntity.ok(categoryRepo.findByLevel_codeAndLevel15OrderByIdDesc(levelCode, category_id));
        } else if (levelCode == 16) {
            return ResponseEntity.ok(categoryRepo.findByLevel_codeAndLevel16OrderByIdDesc(levelCode, category_id));
        } else if (levelCode == 17) {
            return ResponseEntity.ok(categoryRepo.findByLevel_codeAndLevel17OrderByIdDesc(levelCode, category_id));
        } else if (levelCode == 18) {
            return ResponseEntity.ok(categoryRepo.findByLevel_codeAndLevel18OrderByIdDesc(levelCode, category_id));
        } else {
            return null;
        }
    }

    public ResponseEntity<?> GetCategoryLevelWise(int levelCode, String name, int page, int page_size) {
        return ResponseEntity.ok(categoryRepo.findByLevel_codeAndCategory_nameContainsOrderByIdDesc(levelCode, name, PageRequest.of(page, page_size, Sort.by(Sort.Direction.DESC, "id"))));
    }

    public ResponseEntity<?> FindCategoryLevel1(int level2CategoryId) {
        logger.info("Fetching Category for level1 using level2CategoryId: " + level2CategoryId);

        Optional<Category> category = categoryRepo.findById(level2CategoryId);
        if (category.isEmpty()) {
            logger.warn("Category with id " + level2CategoryId + " not found.");

            return ResponseEntity.ok("invalid id !");
        } else {
            logger.info("Category found, retrieving level1 category with id: " + category.get().getLevel1());
            return ResponseEntity.ok(categoryRepo.findById(category.get().getLevel1()));
        }

    }

    public ResponseEntity<?> FindByCategoryLevel3(int page, int pageSize, String name, int level2_id) {
        return ResponseEntity.ok(categoryRepo.findByLevel_codeAndLevel3AndCategory_nameContainsOrderByIdDesc(2,level2_id,name,PageRequest.of(page,pageSize,Sort.by(Sort.Direction.DESC,"id"))));
    }

    public ResponseEntity<?> GetAllCategory(int page, int page_size, int order) {
        return ResponseEntity.ok(categoryRepo.findAll(PageRequest.of(page,page_size, Sort.by(order == 0? Sort.Direction.ASC : Sort.Direction.DESC,"id"))));
    }
}
