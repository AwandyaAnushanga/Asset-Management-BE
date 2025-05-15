package com.nexeyo.erp.jwt.payload.response;

import com.nexeyo.erp.EmployeeAddress.EmployeeAddress;
import com.nexeyo.erp.jwt.models.User;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String refreshToken;
    private List<String> roles;
    private EmployeeAddress employeeAddress;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EmployeeAddress getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(EmployeeAddress employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public JwtResponse(String accessToken, String refreshToken, User user, List<String> roles) {
        this.token = accessToken;
        this.refreshToken = refreshToken;
        this.user=user;
        this.roles = roles;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }


    public List<String> getRoles() {
        return roles;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
