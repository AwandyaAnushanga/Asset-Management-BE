package com.nexeyo.erp.jwt.models;

import com.nexeyo.erp.EmployeeAddress.EmployeeAddress;
import com.nexeyo.erp.UsersLocation.UsersLocation;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String country;
    private String is_verified;
    private Integer company;
    private Integer branch;
    private Integer location_id;
    private LocalDateTime create_at;
    private Integer hr_user_id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    EmployeeAddress employeeAddress;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    List<UsersLocation> usersLocationList;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public EmployeeAddress getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(EmployeeAddress employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public User() {
    }

    public User(String username, String email
            , String password
            , String firstname
            , String lastname
            , String phoneNumber
            , String country
            , String is_verified
            , Integer company
            , Integer branch,
                EmployeeAddress employeeAddress,
                Integer location_id,
                List<UsersLocation> usersLocationList,int hr_user_id

    ) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.is_verified = is_verified;
        this.company = company;
        this.branch = branch;
        this.employeeAddress = employeeAddress;
        this.location_id = location_id;
        this.usersLocationList = usersLocationList;
        this.hr_user_id = hr_user_id;

    }

    public Integer getHr_user_id() {
        return hr_user_id;
    }

    public void setHr_user_id(Integer hr_user_id) {
        this.hr_user_id = hr_user_id;
    }

    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
    }

    public List<UsersLocation> getUsersLocationList() {
        return usersLocationList;
    }

    public void setUsersLocationList(List<UsersLocation> usersLocationList) {
        this.usersLocationList = usersLocationList;
    }

    public LocalDateTime getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDateTime create_at) {
        this.create_at = create_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIs_verified() {
        return is_verified;
    }

    public void setIs_verified(String is_verified) {
        this.is_verified = is_verified;
    }

    public Integer getCompany() {
        return company;
    }

    public void setCompany(Integer company) {
        this.company = company;
    }

    public Integer getBranch() {
        return branch;
    }

    public void setBranch(Integer branch) {
        this.branch = branch;
    }
}
