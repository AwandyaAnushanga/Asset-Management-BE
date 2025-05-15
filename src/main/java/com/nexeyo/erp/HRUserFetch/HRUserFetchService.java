package com.nexeyo.erp.HRUserFetch;

import com.nexeyo.erp.HRDepartment.HRDepartment;
import com.nexeyo.erp.HRDepartment.HRDepartmentRepo;
//import com.nexeyo.erp.SubAccounts.SubAccounts;
//import com.nexeyo.erp.SubAccounts.SubAccountsRepo;
import com.nexeyo.erp.SystemSettings.SystemSettings;
import com.nexeyo.erp.SystemSettings.SystemSettingsRepo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class HRUserFetchService {
    @Autowired
    HRUserFetchRepo hrUserFetchRepo;
    @Autowired
    private HRDepartmentRepo hRDepartmentRepo;
    @Autowired
    private SystemSettingsRepo systemSettingsRepo;
//    @Autowired
//    private SubAccountsRepo subAccountsRepo;

    Logger logger = LoggerFactory.getLogger(HRUserFetchService.class);

    public ResponseEntity<?> Fetch() throws JSONException {
        HrCredentials hrCredentials = new HrCredentials();
//set password
        Optional<SystemSettings> hr_password= systemSettingsRepo.findByFieldIgnoreCase("hr_password");
        hr_password.ifPresent(password -> hrCredentials.setPassword(password.getField_value()));
//        set company id
        Optional<SystemSettings> hr_company_id= systemSettingsRepo.findByFieldIgnoreCase("hr_company_id");
        hr_company_id.ifPresent(company -> hrCredentials.setCompany_id(Integer.valueOf(company.getField_value())));

//        String url ;
        Optional<SystemSettings> hr_employee_url= systemSettingsRepo.findByFieldIgnoreCase("hr_employee_url");
        hr_employee_url.ifPresent(hrUrl -> hrCredentials.setUrl(hrUrl.getField_value()));

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
        ResponseEntity<String> response = restTemplate.postForEntity(hrCredentials.getUrl(), requestEntity, String.class);
        logger.info("Sent POST request to HR API: {}", hrCredentials.getUrl());

        if (response.getStatusCode() == HttpStatus.OK) {
            logger.info("Received successful response from HR API");

            String bodyString = response.getBody();
            JSONArray jsonArray = new JSONArray(bodyString);
            HRUserFetch hrUserFetch = new HRUserFetch();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                hrUserFetch = new HRUserFetch();
                Optional<HRUserFetch> hrUserFetchOptional = hrUserFetchRepo.findByUser_id(Integer.valueOf(obj.getString("user_id")));

                try {

                    if (hrUserFetchOptional.isPresent()) {
                        hrUserFetch = hrUserFetchOptional.get();
                        hrUserFetch.setHrDepartment(null);
                    }
                } catch (Exception e) {
                    logger.error("Error processing existing HR user fetch: ", e);

                }
                try {
                    hrUserFetch.setCompany_id(obj.getInt("company_id"));
                } catch (Exception e) {
                    logger.error("Error setting company_id: ", e);
                }
                try {
                    hrUserFetch.setUser_id(obj.getInt("user_id"));
                } catch (Exception e) {
                    logger.error("Error setting user_id: ", e);
                }
                try {
                    hrUserFetch.setEmployee_id(obj.getString("employee_id"));
                } catch (Exception e) {
                    logger.error("Error setting employee_id: ", e);
                }
                try {
                    hrUserFetch.setEpf_number(obj.getString("epf_number"));
                } catch (Exception e) {
                    logger.error("Error setting epf_number: ", e);
                }
                try {
                    hrUserFetch.setFirst_name(obj.getString("first_name"));
                } catch (Exception e) {
                    logger.error("Error setting first_name: ", e);
                }
                try {
                    hrUserFetch.setLast_name(obj.getString("last_name"));
                } catch (Exception e) {
                    logger.error("Error setting last_name: ", e);
                }
                try {
                    hrUserFetch.setUsername(obj.getString("username"));
                } catch (Exception e) {
                    logger.error("Error setting username: ", e);
                }
                try {
                    hrUserFetch.setEmail(obj.getString("email"));
                } catch (Exception e) {
                    logger.error("Error setting email: ", e);
                }
                try {
                    hrUserFetch.setDate_of_birth(dateFormat.parse(obj.getString("date_of_birth")));
                } catch (Exception e) {
                    logger.error("Error setting date_of_birth: ", e);
                }
                try {
                    hrUserFetch.setGender(obj.getString("gender"));
                } catch (Exception e) {
                    logger.error("Error setting gender: ", e);
                }
                try {
                    hrUserFetch.setE_status(obj.getString("e_status"));
                } catch (Exception e) {
                    logger.error("Error setting e_status: ", e);
                }
                try {
                    hrUserFetch.setUser_role_id(obj.getInt("user_role_id"));
                } catch (Exception e) {
                    logger.error("Error setting user_role_id: ", e);
                }
                try {
                    hrUserFetch.setDepartment_id(obj.getInt("department_id"));
                } catch (Exception e) {
                    logger.error("Error setting department_id: ", e);
                }
                try {
                    hrUserFetch.setDesignation_id(obj.getInt("designation_id"));
                } catch (Exception e) {
                    logger.error("Error setting designation_id: ", e);
                }
                try {
                    hrUserFetch.setCompany_id(obj.getInt("company_id"));
                } catch (Exception e) {
                    logger.error("Error setting company_id: ", e);
                }
                try {
                    hrUserFetch.setDate_of_joining(dateFormat.parse(obj.getString("date_of_joining")));
                } catch (Exception e) {
                    logger.error("Error setting date_of_joining: ", e);
                }

                HRUserFetch hrUserFetchSaved = hrUserFetchRepo.save(hrUserFetch);
                logger.info("Saved HR user fetch: {}", hrUserFetchSaved.getUser_id());

//                if (!hrUserFetchOptional.isPresent()) {
//                    try {
//                        SubAccounts subAccount = new SubAccounts();
//                        subAccount.setEmployee_id(hrUserFetchSaved.getId());
//                        subAccount.setSub_account_type(1);
//                        subAccount.setEmployee_number(hrUserFetchSaved.getEmployee_id());
//                        subAccount.setName(hrUserFetchSaved.getFirst_name() + " " + hrUserFetchSaved.getLast_name());
//                        subAccountsRepo.save(subAccount);
//                        logger.info("Created sub account for new HR user: {}", hrUserFetchSaved.getUser_id());
//                    } catch (Exception e) {
//                        logger.error("Error creating sub account: ", e);
//                    }
//                }


            }

            return ResponseEntity.ok("Success !");

        }

        logger.warn("Failed response from HR API: {}", response.getStatusCode());
        return ResponseEntity.ok(response.getBody());
    }

    public ResponseEntity<?> GetAll(int page, int page_size) {
        return ResponseEntity.ok(hrUserFetchRepo.findAll(PageRequest.of(page, page_size, Sort.by(Sort.Direction.DESC, "id"))));
    }

    public ResponseEntity<?> Search(int page, int pageSize, String name) {
        return ResponseEntity.ok(hrUserFetchRepo.findByFirst_nameContainsAndLast_nameContainsOrderByIdDesc(name, name, PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "id"))));
    }

    public ResponseEntity<?> SearchWithoutPicked(int page, int pageSize, String name) {
        return ResponseEntity.ok(hrUserFetchRepo.findByPicked_for_erpFalseAndPicked_for_erpNullAndFirst_nameContainsAndLast_nameContains(name, name, PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "id"))));
    }

    public ResponseEntity<?> Add(HRUserFetch hrUserFetch) {
        logger.info("Attempting to add HRUserFetch with user_id: {}", hrUserFetch.getUser_id());

        Optional<HRDepartment> hrDepartmentOptional = hRDepartmentRepo.findByDepartment_id(hrUserFetch.getDepartment_id());
        Optional<HRUserFetch> hrUserFetchOptional = hrUserFetchRepo.findByUser_id(hrUserFetch.getUser_id());
        if (hrUserFetchOptional.isPresent()) {
            logger.warn("User ID {} already exists", hrUserFetch.getUser_id());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User id exist !");
        }

        hrUserFetch.setHrDepartment(null);
        try {
            return ResponseEntity.ok(hrUserFetchRepo.save(hrUserFetch));
        } catch (Exception e) {
            logger.error("Error occurred while adding HRUserFetch: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Error : " + e);
        }
    }

    public ResponseEntity<?> Update(HRUserFetch hrUserFetch) {
        logger.info("Attempting to update HRUserFetch with user_id: {}", hrUserFetch.getUser_id());

        hrUserFetch.setHrDepartment(null);
        Optional<HRDepartment> hrDepartmentOptional = hRDepartmentRepo.findByDepartment_id(hrUserFetch.getDepartment_id());
        if (!hrDepartmentOptional.isPresent()) {
            logger.warn("Invalid department for user_id: {}", hrUserFetch.getUser_id());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User doesn't have valid department !");
        }
        Optional<HRUserFetch> hrUserFetchOptional = hrUserFetchRepo.findByUser_id(hrUserFetch.getUser_id());
        if (!hrUserFetchOptional.isPresent()) {
            logger.warn("User ID {} not found for update", hrUserFetch.getUser_id());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User id doesn't exist !");
        } else {
            hrUserFetch.setId(hrUserFetchOptional.get().getId());
            return ResponseEntity.ok(hrUserFetchRepo.save(hrUserFetch));
        }

    }

    public ResponseEntity<?> SearchMeasurementUsers(int page, int pageSize, String name) {
        logger.info("Searching measurement users with name: {}", name);
        Optional<SystemSettings> systemSettingsOptional = systemSettingsRepo.findByFieldIgnoreCase("measurement_role");
        if (!systemSettingsOptional.isPresent()) {
            logger.warn("Measurement role not found in system settings table");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Measurement role not found in system settings table");
        }
        return ResponseEntity.ok(hrUserFetchRepo.findByFirst_nameContainsOrLast_nameContainsAndUser_role_id(name, name, Integer.valueOf(systemSettingsOptional.get().getField_value()), PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "id"))));
   }
    public ResponseEntity<?> Search(String emp_id) {
        try {

            List<HRUserFetch> hrUserFetchList = hrUserFetchRepo.findByUserContainsIgnoreCase(emp_id, PageRequest.of(0, 10));

            return ResponseEntity.ok(hrUserFetchList);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please check provided data !");
        }
    }
}
