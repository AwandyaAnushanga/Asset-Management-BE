package com.nexeyo.erp.NumberingSystem;

import com.nexeyo.erp.SystemTypes.SystemTypes;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "numbering_system")
public class NumberingSystem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private Integer type;
    @Column
    private String typeName;
    @Column
    private Integer lastNo;
    @Column
    private Integer lastDocumentNo;
    @Column
    private String startCharacter;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "type", referencedColumnName = "id", insertable = false, updatable = false)
    private SystemTypes systemTypes;



}
