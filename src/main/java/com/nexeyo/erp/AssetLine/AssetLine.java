package com.nexeyo.erp.AssetLine;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class AssetLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private Integer header_id;
    @Column
    private Integer createBy;
    @Column
    private Integer updateBy;
    @Column
    private LocalDateTime createAt;
    @Column
    private LocalDateTime updateAt;
    @Column
    private String updateReason;
    @Column
    private Integer custodian_id;
    @Column
    private String assertNo;
    @Column
    private Integer assertNoWithoutCharacters;
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean approved;
    @Column
    private Double maintenanceCost;
}
