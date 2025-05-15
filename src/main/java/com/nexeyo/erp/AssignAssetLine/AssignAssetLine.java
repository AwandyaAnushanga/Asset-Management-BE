package com.nexeyo.erp.AssignAssetLine;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class AssignAssetLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer header_id;
    private Integer assignedUser;
    private LocalDateTime createAt;
    private Integer createdBy;
    private LocalDateTime updateAt;
    private Integer updateBy;

}
