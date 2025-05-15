package com.nexeyo.erp.HRDepartment;

import com.nexeyo.erp.HRUserFetch.HrCredentials;
import com.nexeyo.erp.SystemSettings.SystemSettingsRepo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Optional;

@Service
public class HRDepartmentService {

    private final HRDepartmentRepo hRDepartmentRepo;
    private final SystemSettingsRepo systemSettingsRepo;

    Logger logger = LoggerFactory.getLogger(HRDepartmentService.class);

    public HRDepartmentService(HRDepartmentRepo hRDepartmentRepo,
                               SystemSettingsRepo systemSettingsRepo) {
        this.hRDepartmentRepo = hRDepartmentRepo;
        this.systemSettingsRepo = systemSettingsRepo;
    }

    public ResponseEntity<?> Fetch() throws JSONException {
        logger.info("Fetching HR department data");

        HrCredentials hrCredentials = new HrCredentials();
        systemSettingsRepo.findByFieldIgnoreCase("hr_password").ifPresent(hr_password -> hrCredentials.setPassword(hr_password.getField_value()));
        systemSettingsRepo.findByFieldIgnoreCase("hr_company_id").ifPresent(hr_company_id -> hrCredentials.setCompany_id(Integer.valueOf(hr_company_id.getField_value())));
        systemSettingsRepo.findByFieldIgnoreCase("hr_department_url").ifPresent(hr_department_url -> hrCredentials.setUrl(hr_department_url.getField_value()));
//        String url = "https://ziglobal.nexhris.com/ziglobal/erp/hr/departments";
        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create request body
        String requestBody = "{\"password\": \"" + hrCredentials.getPassword() + "\", \"company_id\": " + hrCredentials.getCompany_id() + "}";

        // Create HttpEntity with headers and body
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Create RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Send POST request
        logger.info("Sending request to: " + hrCredentials.getUrl());
        ResponseEntity<String> response = restTemplate.postForEntity(hrCredentials.getUrl(), requestEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            logger.info("Response received successfully from HR system");

            String bodyString = response.getBody();
            JSONArray jsonArray = new JSONArray(bodyString);
            HRDepartment hrDepartment = new HRDepartment();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                hrDepartment = new HRDepartment();

                try {
                    Optional<HRDepartment> hrDepartmentOptional = hRDepartmentRepo.findByDepartment_id(obj.getInt("department_id"));
                    if (hrDepartmentOptional.isPresent()) {
                        hrDepartment = hrDepartmentOptional.get();
                    }
                } catch (Exception e) {
                    logger.error("Error finding department by ID", e);
                }
//                try {
//                    hrDepartment.setHr_id(obj.getInt("id"));
//                } catch (Exception e) {
//                }
                try {
                    hrDepartment.setDepartment_id(obj.getInt("department_id"));
                } catch (Exception e) {
                    logger.error("Error setting department_id", e);
                }
                try {
                    hrDepartment.setDepartment_name(obj.getString("department_name"));

                } catch (Exception e) {
                    logger.error("Error setting department_name", e);
                }
                try {
                    hrDepartment.setCompany_id(obj.getInt("company_id"));

                } catch (Exception e) {
                    logger.error("Error setting company_id", e);
                }
                try {
                    hrDepartment.setLocation_id(obj.getInt("location_id"));

                } catch (Exception e) {
                    logger.error("Error setting location_id", e);
                }
                try {
                    hrDepartment.setEmployee_id(obj.getInt("employee_id"));

                } catch (Exception e) {
                    logger.error("Error setting employee_id", e);
                }
                try {
                    hrDepartment.setAdded_by(obj.getInt("added_by"));

                } catch (Exception e) {
                    logger.error("Error setting added_by", e);
                }
                try {
                    hrDepartment.setCreated_at(dateFormat.parse(obj.getString("created_at")));

                } catch (Exception e) {
                    logger.error("Error setting created_at", e);
                }
                try {
                    hrDepartment.setStatus(obj.getInt("status"));
                } catch (Exception e) {
                    logger.error("Error setting status", e);
                }
                hRDepartmentRepo.save(hrDepartment);
                logger.info("HR department saved: " + hrDepartment);
            }
            return ResponseEntity.ok("Success !");

        }
        logger.warn("Failed to fetch data from HR system");
        return ResponseEntity.ok("Failed From HR Side !");

    }

    public ResponseEntity<?> Search(int page, int pageSize, String department_name) {
        return ResponseEntity.ok(hRDepartmentRepo.findByDepartment_nameContainsOrderByIdDesc(department_name, PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "id"))));
    }

    public ResponseEntity<?> Add(HRDepartment hrDepartment) {
        logger.info("Received request to add HR department: " + hrDepartment);


        Optional<HRDepartment> hrDepartmentOptional = hRDepartmentRepo.findByDepartment_id(hrDepartment.getDepartment_id());
        if (hrDepartmentOptional.isPresent()) {
            logger.warn("Department ID already exists: " + hrDepartment.getDepartment_id());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Department id already exist !");
        }

        try {
            return ResponseEntity.ok(hRDepartmentRepo.save(hrDepartment));
        } catch (Exception e) {
            logger.error("Error adding HR department: ", e);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Error : " + e);
        }
    }

    public ResponseEntity<?> Update(HRDepartment hrDepartment) {

        logger.info("Received request to update HR department: " + hrDepartment);
        Optional<HRDepartment> hrDepartmentOptional = hRDepartmentRepo.findByDepartment_id(hrDepartment.getDepartment_id());

        if (!hrDepartmentOptional.isPresent()) {
            logger.warn("Department not found: " + hrDepartment.getDepartment_id());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("department doesn't exist !");
        } else {
            hrDepartment.setId(hrDepartmentOptional.get().getId());
            logger.info("HR department updated successfully" );
            return ResponseEntity.ok(hRDepartmentRepo.save(hrDepartment));
        }

    }
}
