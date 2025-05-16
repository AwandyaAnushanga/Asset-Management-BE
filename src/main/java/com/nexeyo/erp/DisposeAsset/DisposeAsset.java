package com.nexeyo.erp.DisposeAsset;

import com.nexeyo.erp.AssetLine.AssetLine;
import com.nexeyo.erp.DisposeTypes.DisposeTypes;
import com.nexeyo.erp.jwt.models.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class DisposeAsset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private Integer asset_line_id;
    @Column
    private Integer type_id;
    @Column
    private String description;
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
    @Column
    private Double salePrice;
    @Column
    private Double gainValue;
    @Column
    private Double lossValue;
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean gain;
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean loss;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_line_id", columnDefinition = "id", insertable = false, updatable = false)
    private AssetLine assetLine;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", columnDefinition = "id", insertable = false, updatable = false)
    private DisposeTypes disposeTypes;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "id", insertable = false, updatable = false)
    private User user;
}
