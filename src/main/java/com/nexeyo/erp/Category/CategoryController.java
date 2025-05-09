package com.nexeyo.erp.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping(path = "")
    ResponseEntity<?> Add(@RequestBody Category category) {
        return categoryService.Add(category);
    }

    @GetMapping(path = "/{level_code}/{category_id}")
    ResponseEntity<?> GetCategoryLevelCodeWise(@PathVariable int level_code, @PathVariable int category_id) {
        return categoryService.GetByLevelCode(level_code, category_id);
    }

    @PostMapping(path = "search-by-category/{page}/{page_size}")
    ResponseEntity<?> GetCategoryLevelWise(@RequestParam int level_code, @RequestParam String name, @PathVariable int page, @PathVariable int page_size) {
        return categoryService.GetCategoryLevelWise(level_code, name, page, page_size);
    }

    @GetMapping(path = "find-category-level1/{level2_category_id}")
    ResponseEntity<?> FindCategoryLevel1(@PathVariable int level2_category_id) {
        return categoryService.FindCategoryLevel1(level2_category_id);
    }

    @PostMapping(path = "search-category-level3/{page}/{page_size}")
    ResponseEntity<?> FindByCategoryLevel3(@PathVariable int page, @PathVariable int page_size, @RequestParam String name, @RequestParam int level2_id) {
        return categoryService.FindByCategoryLevel3(page, page_size, name, level2_id);
    }

    @GetMapping("/all/{page}/{page_size}/{order}")
    public ResponseEntity<?> GetAllCategory (@PathVariable int page, @PathVariable int page_size, @PathVariable int order){
        return categoryService.GetAllCategory(page, page_size, order);
    }
}
