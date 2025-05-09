package com.nexeyo.erp.Category;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

    @Query("select c from Category c " +
            "where c.level_code = ?1 and c.level2 = ?2 and c.category_name like concat('%', ?3, '%') " +
            "order by c.id DESC")
    List<Category> findByLevel_codeAndLevel3AndCategory_nameContainsOrderByIdDesc(Integer level_code, Integer level3, String category_name, Pageable pageable);
    @Query("select c from Category c " +
            "where c.level_code = ?1 and c.category_name like concat('%', ?2, '%') " +
            "order by c.id DESC")
    List<Category> findByLevel_codeAndCategory_nameContainsOrderByIdDesc(Integer level_code, String category_name, Pageable pageable);
    @Query("select c from Category c where c.level_code = ?1 and c.level18 = ?2 order by c.id DESC")
    List<Category> findByLevel_codeAndLevel18OrderByIdDesc(Integer level_code, Integer level18);
    @Query("select c from Category c where c.level_code = ?1 and c.level17 = ?2 order by c.id DESC")
    List<Category> findByLevel_codeAndLevel17OrderByIdDesc(Integer level_code, Integer level17);
    @Query("select c from Category c where c.level_code = ?1 and c.level16 = ?2 order by c.id DESC")
    List<Category> findByLevel_codeAndLevel16OrderByIdDesc(Integer level_code, Integer level16);
    @Query("select c from Category c where c.level_code = ?1 and c.level15 = ?2 order by c.id DESC")
    List<Category> findByLevel_codeAndLevel15OrderByIdDesc(Integer level_code, Integer level15);
    @Query("select c from Category c where c.level_code = ?1 and c.level14 = ?2 order by c.id DESC")
    List<Category> findByLevel_codeAndLevel14OrderByIdDesc(Integer level_code, Integer level14);
    @Query("select c from Category c where c.level_code = ?1 and c.level13 = ?2 order by c.id DESC")
    List<Category> findByLevel_codeAndLevel13OrderByIdDesc(Integer level_code, Integer level13);
    @Query("select c from Category c where c.level_code = ?1 and c.level12 = ?2 order by c.id DESC")
    List<Category> findByLevel_codeAndLevel12OrderByIdDesc(Integer level_code, Integer level12);
    @Query("select c from Category c where c.level_code = ?1 and c.level11 = ?2 order by c.id DESC")
    List<Category> findByLevel_codeAndLevel11OrderByIdDesc(Integer level_code, Integer level11);
    @Query("select c from Category c where c.level_code = ?1 and c.level10 = ?2 order by c.id DESC")
    List<Category> findByLevel_codeAndLevel10OrderByIdDesc(Integer level_code, Integer level10);
    @Query("select c from Category c where c.level_code = ?1 and c.level9 = ?2 order by c.id DESC")
    List<Category> findByLevel_codeAndLevel9OrderByIdDesc(Integer level_code, Integer level9);
    @Query("select c from Category c where c.level_code = ?1 and c.level8 = ?2 order by c.id DESC")
    List<Category> findByLevel_codeAndLevel8OrderByIdDesc(Integer level_code, Integer level8);
    @Query("select c from Category c where c.level_code = ?1 and c.level7 = ?2 order by c.id DESC")
    List<Category> findByLevel_codeAndLevel7OrderByIdDesc(Integer level_code, Integer level7);
    @Query("select c from Category c where c.level_code = ?1 and c.level6 = ?2 order by c.id DESC")
    List<Category> findByLevel_codeAndLevel6OrderByIdDesc(Integer level_code, Integer level6);
    @Query("select c from Category c where c.level_code = ?1 and c.level5 = ?2 order by c.id DESC")
    List<Category> findByLevel_codeAndLevel5OrderByIdDesc(Integer level_code, Integer level5);
    @Query("select c from Category c where c.level_code = ?1 and c.level4 = ?2 order by c.id DESC")
    List<Category> findByLevel_codeAndLevel4OrderByIdDesc(Integer level_code, Integer level4);
    @Query("select c from Category c where c.level_code = ?1 and c.level3 = ?2 order by c.id DESC")
    List<Category> findByLevel_codeAndLevel3OrderByIdDesc(Integer level_code, Integer level3);
    @Query("select c from Category c where c.level_code = ?1 and c.level2 = ?2 order by c.id DESC")
    List<Category> findByLevel_codeAndLevel2OrderByIdDesc(Integer level_code, Integer level2);
    @Query("select c from Category c where c.level_code = ?1 and c.level1 = ?2 order by c.id DESC")
    List<Category> findByLevel_codeAndLevel1OrderByIdDesc(Integer level_code, Integer level1);
    @Query("select c from Category c where c.level_code = ?1 order by c.id DESC")
    List<Category> findByLevel_codeOrderByIdDesc(Integer level_code);

    @Query("select c from Category c where c.level_code = ?1")
    List<Category> findByLevel_code(Integer level_code);

    @Query("select c from Category c where c.level_code = ?1 and report_filter = 1")
    List<Category> findByLevel_codeAndReportFilter(Integer level_code);

    @Query("SELECT c.id, c.category_name FROM Category c")
    List<Object[]> findAllCategories();
}
