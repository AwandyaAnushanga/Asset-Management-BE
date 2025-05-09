package com.nexeyo.erp.Category;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column
    private String category_name;
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean sub_category;
    @Column
    private Integer level_code;
    @Column
    private Integer level1;
    @Column
    private Integer level2;
    @Column
    private Integer level3;
    @Column
    private Integer level4;
    @Column
    private Integer level5;
    @Column
    private Integer level6;
    @Column
    private Integer level7;
    @Column
    private Integer level8;
    @Column
    private Integer level9;
    @Column
    private Integer level10;
    @Column
    private Integer level11;
    @Column
    private Integer level12;
    @Column
    private Integer level13;
    @Column
    private Integer level14;
    @Column
    private Integer level15;
    @Column
    private Integer level16;
    @Column
    private Integer level17;
    @Column
    private Integer level18;

    //category short key
    @Column
    private String short_key;


//    @ApiModelProperty(hidden = true)
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
//    private List<CategoryFields> categoryFields;

    @Transient
    private Double noOfPcs;
    @Transient
    private Double amount;
    @Transient
    private Double gross;
    @Transient
    private Double net;

    @Column(columnDefinition = "TINYINT(1)")
    private Boolean report_filter;
}
