package com.nexeyo.erp.HRDepartment;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class HRDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column
    private Integer department_id;
    @Column
    private String department_name;
    @Column
    private Integer company_id;
    @Column
    private Integer location_id;
    @Column
    private Integer employee_id;
    @Column
    private Integer added_by;
    @Column
    private Date created_at;
    @Column
    private Integer status;



}
