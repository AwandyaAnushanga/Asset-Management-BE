package com.nexeyo.erp.UsersLocation;

import com.nexeyo.erp.Location.Location;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class UsersLocation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column
    Integer user_id;
    @Column
    Integer location_id;

    @OneToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id",insertable = false,updatable = false)
    private Location location;
}
