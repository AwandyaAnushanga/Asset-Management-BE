package com.nexeyo.erp.AssetStatus;

import lombok.Data;
import org.hibernate.annotations.Cache;

import javax.persistence.*;

@Data
@Entity
public class AssetStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String name;
    private String description;
}
