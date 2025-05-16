package com.nexeyo.erp.AssetTransfer;

import com.nexeyo.erp.AssetLine.AssetLine;
import com.nexeyo.erp.Location.Location;
import com.nexeyo.erp.jwt.models.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class AssetTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private Integer fromLocation;
    @Column
    private Integer toLocation;
    @Column
    private Integer qty;
    @Column
    private LocalDateTime createAt;
    @Column
    private Integer createBy;
    @Column
    private Integer asset_line_id;
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean approved;
    @Column
    private Integer approvedBy;
    @Column
    private LocalDateTime approvedAt;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_line_id", referencedColumnName = "id", insertable = false, updatable = false)
    private AssetLine assetLine;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Location location;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;
}
