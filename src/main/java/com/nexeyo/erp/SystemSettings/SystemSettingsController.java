package com.nexeyo.erp.SystemSettings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/system_settings/")
public class SystemSettingsController {
    @Autowired
    SystemSettingsService systemSettingsService;

    @PostMapping(path = "set-company-vat-payable")
    ResponseEntity<?> SetCompanyVatPayable(@RequestParam int id) {
        return systemSettingsService.SetCompanyVatPayable(id);
    }

    @PostMapping(path = "set-company-vat-receivable")
    ResponseEntity<?> SetCompanyVatReceivable(@RequestParam int id) {
        return systemSettingsService.SetCompanyVatReceivable(id);
    }

//    @PostMapping(path = "currency")
//    ResponseEntity<?> SetSystemCurrency(@RequestParam int id) {
//        return systemSettingsService.SetSystemCurrency(id);
//    }

//    @GetMapping(path = "currency")
//    ResponseEntity<?> getSystemCurrency() {
//        return systemSettingsService.GetSystemCurrency();
//    }

    @PostMapping(path = "set-cost-update-start-number")
    ResponseEntity<?> setCostUpdateStartNumber(@RequestParam String number) {
        return systemSettingsService.setCostUpdateStartNumber(number);
    }

    @GetMapping(path = "get-cost-update-start-number")
    ResponseEntity<?> getCostUpdateStartNumber() {
        return systemSettingsService.getCostUpdateStartNumber();
    }

    @GetMapping(path = "get-cost-update-start-character")
    ResponseEntity<?> getCostUpdateStartCharacter() {
        return systemSettingsService.getCostUpdateStartCharacter();
    }

    @PostMapping(path = "set-cost-update-start-character")
    ResponseEntity<?> setCostUpdateStartCharacter(@RequestParam String character) {
        return systemSettingsService.setCostUpdateStartCharacter(character);
    }

    @PostMapping(path = "set-balance-sheet-group-type")
    ResponseEntity<?> setBalanceSheetGroupType(@RequestParam String type) {
        return systemSettingsService.setBalanceSheetGroupType(type);
    }

    @GetMapping(path = "get-balance-sheet-group-type")
    ResponseEntity<?> getBalanceSheetGroupType() {
        return systemSettingsService.getBalanceSheetGroupType();
    }

    @PostMapping(path = "set-pnl")
    ResponseEntity<?> setPNL(@RequestParam String type) {
        return systemSettingsService.setPNL(type);
    }

    @GetMapping(path = "get-pnl")
    ResponseEntity<?> getPNL() {
        return systemSettingsService.getPNL();
    }

//    @PostMapping(path = "inventory-suspend")
//    ResponseEntity<?> setInventorySuspend(@RequestParam String id) {
//        return systemSettingsService.setInventorySuspend(id);
//    }

//    @GetMapping(path = "inventory-suspend")
//    ResponseEntity<?> getInventorySuspend() {
//        return systemSettingsService.getInventorySuspend();
//    }

//    @PostMapping(path = "common-cash-receipt")
//    ResponseEntity<?> setCommonCashReceipt(@RequestParam String name) {
//        return systemSettingsService.setCommonCashReceipt(name);
//    }

//    @GetMapping(path = "common-cash-receipt")
//    ResponseEntity<?> getCommonReceipt() {
//        return systemSettingsService.getCommonCashReceipt();
//    }

//    @PostMapping(path = "common-bank-receipt")
//    ResponseEntity<?> setCommonBankReceipt(@RequestParam String name) {
//        return systemSettingsService.setCommonBankReceipt(name);
//    }

//    @GetMapping(path = "common-bank-receipt")
//    ResponseEntity<?> getCommonBankReceipt() {
//        return systemSettingsService.getCommonBankReceipt();
//    }


//    @GetMapping(path = "vendor-payable-controller-account")
//    ResponseEntity<?> getVendorPayableControllerAccount() {
//        return systemSettingsService.getVendorPayableControllerAccount();
//    }


//    @PostMapping(path = "vendor-payable-controller-account")
//    ResponseEntity<?> setVendorPayableControllerAccount(@RequestParam String name) {
//        return systemSettingsService.setVendorPayableControllerAccount(name);
//    }

//    @GetMapping(path = "vendor-receivable-controller-account")
//    ResponseEntity<?> getVendorReceivableControllerAccount() {
//        return systemSettingsService.getVendorReceivableControllerAccount();
//    }
//
//
//    @PostMapping(path = "vendor-receivable-controller-account")
//    ResponseEntity<?> setVendorReceivableControllerAccount(@RequestParam String name) {
//        return systemSettingsService.setVendorReceivableControllerAccount(name);
//    }
//
//    @GetMapping(path = "fiscal-year")
//    ResponseEntity<?> getCurrentFiscalYear() {
//        return systemSettingsService.getCurrentFiscalYear();
//    }
//
//
//    @PostMapping(path = "fiscal-year")
//    ResponseEntity<?> setCurrentFiscalYear(@RequestParam int fiscal_year_id) {
//        return systemSettingsService.setCurrentFiscalYear(fiscal_year_id);
//    }
//
//    @PostMapping(path = "fiscal-year-start-month")
//    ResponseEntity<?> setFiscalYearStartPeriod(@RequestParam int fiscal_year_start_month) {
//        return systemSettingsService.setFiscalYearStartPeriod(fiscal_year_start_month);
//    }


}
