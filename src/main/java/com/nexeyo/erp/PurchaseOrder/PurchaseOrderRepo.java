package com.nexeyo.erp.PurchaseOrder;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrder,Integer> {
    @Query("select p from PurchaseOrder p where p.approved = ?1 order by p.id DESC")
    List<PurchaseOrder> findByApprovedOrderByIdDesc(Boolean approved, Pageable pageable);
    @Query("select p from PurchaseOrder p where p.create_at >= ?1 and p.create_at <= ?2 order by p.id DESC")
    List<PurchaseOrder> findByCreate_atGreaterThanEqualAndCreate_atLessThanEqualOrderByIdDesc(LocalDateTime create_at, LocalDateTime create_at1);
    @Query("select p from PurchaseOrder p where p.po_date >= ?1 and p.po_date <= ?2 order by p.id DESC")
    List<PurchaseOrder> findByPo_dateGreaterThanEqualAndPo_dateLessThanEqualOrderByIdDesc(LocalDate po_date, LocalDate po_date1, Pageable pageable);
    @Query("select p from PurchaseOrder p where p.po_date >= ?1 and p.po_date <= ?2")
    List<PurchaseOrder> findByPo_dateGreaterThanEqualAndPo_dateLessThanEqual(LocalDate po_date, LocalDate po_date1);

    @Query("select p from PurchaseOrder p " +
            "where p.supplier_id = ?1 and p.po_no like concat('%', ?2, '%') and p.location.id = ?3")
    List<PurchaseOrder> findBySupplier_idAndPo_noContainsAndLocation_id(Integer supplier_id, String po_no, Integer location_id, Pageable pageable);

    @Query("select p from PurchaseOrder p " +
            "where p.supplier_id = ?1 and p.po_no like concat('%', ?2, '%') and p.location.id = ?3 and p.approved= ?4 and p.back_order_status !=5")
    List<PurchaseOrder> findBySupplier_idAndPo_noContainsAndLocation_id(Integer supplier_id, String po_no, Integer location_id, boolean status, Pageable pageable);

    @Query("select p from PurchaseOrder p " +
            "where p.supplier_id = ?1 and p.back_order_status !=5 and p.po_no like concat('%', ?2, '%') and p.location.id = ?3")
    List<PurchaseOrder> findBySupplier_idAndPo_noContainsAndLocation_idStatus5(Integer supplier_id, String po_no, Integer location_id, Pageable pageable);

    @Query("select p from PurchaseOrder p " +
            "where p.supplier_id = ?1 and p.back_order_status !=5 and p.po_no like concat('%', ?2, '%') and p.location.id = ?3 and (p.approved=true or p.approved=true)")
    List<PurchaseOrder> findBySupplier_idAndPo_noContainsAndLocation_idStatus5Active(Integer supplier_id, String po_no, Integer location_id, Pageable pageable);

    @Query("select count(p) from PurchaseOrder p")
    long countFirstBy();
    @Query("select p from PurchaseOrder p where p.supplier_id = ?1 and p.id like concat('%', ?2, '%')")
    List<PurchaseOrder> findBySupplier_idAndIdIn(int supplier_id, String ids, Pageable pageable);
    @Query("select p from PurchaseOrder p where p.supplier_id = ?1")
    List<PurchaseOrder> findBySupplier_id(int supplier_id, Pageable pageable);

    @Query(nativeQuery = true,value = "SELECT MAX(po_no_without_characters) FROM purchase_order")
    Integer geMaxValue();

    @Query("select p from PurchaseOrder p where p.po_no = ?1")
    Optional<PurchaseOrder> findByPo_no(String po_no);
}
