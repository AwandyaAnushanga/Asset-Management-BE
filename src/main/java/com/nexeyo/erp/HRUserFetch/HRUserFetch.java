package com.nexeyo.erp.HRUserFetch;

import com.nexeyo.erp.HRDepartment.HRDepartment;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class HRUserFetch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column
    private Integer user_id;
    @Column
    private String employee_id;
    @Column
    private String epf_number;
    @Column
    private String first_name;
    @Column
    private String last_name;
    @Column
    private String username;
    @Column
    private String email;
    @Column
    private Date date_of_birth;
    @Column
    private String gender;
    @Column
    private String e_status;
    @Column
    private Integer user_role_id;
    @Column
    private Integer department_id;
    @Column
    private Integer designation_id;
    @Column
    private Integer company_id;
    @Column
    private Date date_of_joining;
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean picked_for_erp;

//    @ApiModelProperty(hidden = true)
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "department_id", referencedColumnName = "id", insertable = false, updatable = false)
//    private HRDepartment hrDepartment;
    @Transient
    private HRDepartment hrDepartment;
//    @Transient
//    private HRDepartment hrDepartment;
}
