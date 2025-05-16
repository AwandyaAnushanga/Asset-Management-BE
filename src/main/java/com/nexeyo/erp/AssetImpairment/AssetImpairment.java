package com.nexeyo.erp.AssetImpairment;

import com.nexeyo.erp.AssetLine.AssetLine;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class AssetImpairment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private Integer asset_line_id;
    @Column
    private Double impairment;
    @Column
    private LocalDateTime createAt;
    @Column
    private Integer createBy;
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean approved;
    @Column
    private Integer approvedBy;
    @Column
    private LocalDateTime approvedAt;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_line_id", referencedColumnName = "id", insertable = false, updatable = false)
    private AssetLine assetLine;

}
