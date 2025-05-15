package com.nexeyo.erp.AssetMaintenance;

import com.nexeyo.erp.AssetLine.AssetLine;
import com.nexeyo.erp.MaintenanceType.MaintenanceType;
import com.nexeyo.erp.Technician.Technician;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class AssetMaintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private Integer technician_id;
    @Column
    private Integer asset_line_id;
    @Column
    private String description;
    @Column
    private Integer type;
    @Column
    private LocalDateTime createAt;
    @Column
    private Integer createBy;
    @Column
    private LocalDateTime updateAt;
    @Column
    private Integer updateBy;
    @Column
    private LocalDateTime nextServiceDate;
    @Column
    private Double cost;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "technician_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Technician technician;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "type", referencedColumnName = "id", insertable = false, updatable = false)
    private MaintenanceType maintenanceType;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_line_id", referencedColumnName = "id", insertable = false, updatable = false)
    private AssetLine assetLine;

}
