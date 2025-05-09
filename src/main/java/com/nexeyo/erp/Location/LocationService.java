package com.nexeyo.erp.Location;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepo locationRepo;

    Logger logger = LoggerFactory.getLogger(LocationService.class);

    public ResponseEntity<?> AddLocation(Location location) {


        try {
            try {
                if (location.getId() == null || location.getId() == 0) {
                    location.setId(0);
                }
            } catch (Exception e) {
            }
            if (locationRepo.findById(location.getId()).isPresent()) {
                logger.info("AddLocation - Location already exist !");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Location already exist !");
            }

            Integer latestNo = 1;
            try {
                Integer location1 = locationRepo.getMax();
                if (location1 == null) {
                    latestNo = 1;
                } else {
                    latestNo = location1+1;
                }
            } catch (Exception e) {
            }

            location.setBranch_code_without_characters(latestNo);
            if (latestNo < 10) {
                location.setBranch_code("0" + latestNo);
            } else {
                location.setBranch_code(String.valueOf(latestNo));
            }

            return ResponseEntity.ok(locationRepo.save(location));


//            try {
//
//
//                AccFinanceAccounts payable = new AccFinanceAccounts();
//                payable.setIs_supplier(false);
//                payable.setAcc_code(locationSaved.getId().toString());
//                payable.setName(locationSaved.getName() + "Payable");
//                payable.setCategory_level(2);
//                payable.setLevel_1_id(2);
//                payable.setLevel_2_id(16);
//                payable.setLevel_3_id(21);
//                payable.setCreate_at(LocalDateTime.now());
//                payable.setLocation_id(locationSaved.getId());
//                AccFinanceAccounts savedPayable = accFinanceAccountsRepo.save(payable);
//                locationSaved.setPayable_account(savedPayable.getId());
//
//
//                AccFinanceAccounts receivable = new AccFinanceAccounts();
//                receivable.setIs_supplier(false);
//                receivable.setAcc_code(locationSaved.getId().toString());
//                receivable.setName(locationSaved.getName() + "Receivable");
//                receivable.setCategory_level(2);
//                receivable.setLevel_1_id(1);
//                receivable.setLevel_2_id(1);
//                receivable.setLevel_3_id(3);
//                receivable.setLocation_id(locationSaved.getId());
//                receivable.setCreate_at(LocalDateTime.now());
//                AccFinanceAccounts savedReceivable = accFinanceAccountsRepo.save(receivable);
//                locationSaved.setReceivable_account(savedReceivable.getId());
//
//
//                AccFinanceAccounts cash = new AccFinanceAccounts();
//                cash.setIs_supplier(false);
//                cash.setAcc_code(locationSaved.getId().toString());
//                cash.setName(locationSaved.getName() + "Cash");
//                cash.setCategory_level(2);
//                cash.setLevel_1_id(1);
//                cash.setLevel_2_id(1);
//                cash.setLevel_3_id(1);
//                cash.setLocation_id(locationSaved.getId());
//                cash.setCreate_at(LocalDateTime.now());
//                AccFinanceAccounts savedCash = accFinanceAccountsRepo.save(cash);
//                locationSaved.setCash_account(savedCash.getId());
//
//
//                AccFinanceAccounts sale = new AccFinanceAccounts();
//                sale.setIs_supplier(false);
//                sale.setAcc_code(locationSaved.getId().toString());
//                sale.setName(locationSaved.getName() + "RetailSale");
//                sale.setCategory_level(2);
//                sale.setLevel_1_id(4);
//                sale.setLevel_2_id(23);
//                sale.setLevel_3_id(36);
//                sale.setLocation_id(locationSaved.getId());
//                sale.setCreate_at(LocalDateTime.now());
//                AccFinanceAccounts savedSale = accFinanceAccountsRepo.save(sale);
//                locationSaved.setRetail_sale_account(savedSale.getId());
//                AccFinanceAccounts savedSale1 = accFinanceAccountsRepo.save(sale);
//                locationSaved.setCash_account(savedSale1.getId());
//
//
//            } catch (Exception e) {
//                logger.error("Error : " + e);
//                ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Error : " + e);
//            }
//
//            Location locationSaved1 = locationRepo.save(locationSaved);
//
//            logger.info("AddLocation - Success");
//            return ResponseEntity.ok("success");

        } catch (Exception e) {
            logger.error("AddLocation Error : Something went wrong !. Please contact develop team.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong !. Please contact develop team. ");
        }
    }

    public ResponseEntity<?> UpdateLocation(Location location) {
        try {
            Optional<Location> location1 = locationRepo.findById(location.getId());
            if (!location1.isPresent()) {
                logger.info("Location id not found !");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Location id not found ");
            }
            locationRepo.save(location);
            logger.info("UpdateLocation - Success");
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            logger.error("UpdateLocation - Something went wrong !. Please contact develop team. ");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong !. Please contact develop team. ");
        }
    }

    public ResponseEntity<?> Get(int page, int pageSize, int order) {
        try {
            Page<Location> location = locationRepo.findAll(PageRequest.of(page, pageSize, Sort.by(order == 0 ? Sort.Direction.ASC : Sort.Direction.DESC, "id")));
            logger.info("Get - Success");
            return ResponseEntity.ok(location);

        } catch (Exception e) {
            logger.error("Get - " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : " + e);

        }
    }

    public ResponseEntity<?> GetById(int location_id) {
        try {
            Optional<Location> location = locationRepo.findById(location_id);
            logger.info("GetById - Success");
            return ResponseEntity.ok(location);

        } catch (Exception e) {
            logger.error("GetById - " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : " + e);
        }
    }
}
