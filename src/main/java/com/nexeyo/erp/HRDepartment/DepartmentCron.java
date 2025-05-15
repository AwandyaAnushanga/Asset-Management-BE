package com.nexeyo.erp.HRDepartment;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DepartmentCron {
    @Autowired
    HRDepartmentService hrDepartmentService;
    @Autowired
    private SessionFactory sessionFactory;

    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void GetUsers() throws JSONException {
        Session session = sessionFactory.openSession();
        try {
            hrDepartmentService.Fetch();
        } finally {
            session.close();
        }
    }
}
