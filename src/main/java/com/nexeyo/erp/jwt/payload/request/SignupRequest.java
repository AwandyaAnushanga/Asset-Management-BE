package com.nexeyo.erp.jwt.payload.request;

import com.nexeyo.erp.EmployeeAddress.EmployeeAddress;
import com.nexeyo.erp.UsersLocation.UsersLocation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

public class SignupRequest {
    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    private String password;

    private String firstname;
    private String lastname;
    private String phoneNumber;

    private String country;
    private String is_verified;
    private Integer company;
    private Integer branch;

    private EmployeeAddress employeeAddress;

    private List<UsersLocation> usersLocationList;
    private Integer user_location_id;

    private Integer hr_user_id;

    public Integer getHr_user_id() {
        return hr_user_id;
    }

    public void setHr_user_id(Integer hr_user_id) {
        this.hr_user_id = hr_user_id;
    }

    public EmployeeAddress getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(EmployeeAddress employeeAddress) {
        this.employeeAddress = employeeAddress;
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

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
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

    public List<UsersLocation> getUsersLocationList() {
        return usersLocationList;
    }

    public void setUsersLocationList(List<UsersLocation> usersLocationList) {
        this.usersLocationList = usersLocationList;
    }

    public Integer getUser_location_id() {
        return user_location_id;
    }

    public void setUser_location_id(Integer user_location_id) {
        this.user_location_id = user_location_id;
    }
}
