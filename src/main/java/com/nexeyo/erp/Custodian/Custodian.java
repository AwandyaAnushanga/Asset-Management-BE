package com.nexeyo.erp.Custodian;

import com.nexeyo.erp.Location.Location;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Custodian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String phoneNumber;
    @Column
    private String designation;
    @Column
    private String employeeNo;
    @Column
    private Integer location_id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", referencedColumnName = "id", insertable = false,updatable = false)
    private Location location;

}
