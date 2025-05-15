package com.nexeyo.erp.AssignAssetHeader;

import com.nexeyo.erp.AssignAssetLine.AssignAssetLine;
import com.nexeyo.erp.Location.Location;
import com.nexeyo.erp.jwt.models.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class AssignAssetHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer assignedUser;
    private Integer location_id;
    private LocalDateTime createAt;
    private Integer createdBy;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Location location;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "header_id", referencedColumnName = "id", insertable = false, updatable = false)
    private AssignAssetLine assignAssetLine;
}
