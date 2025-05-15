package com.nexeyo.erp.SystemSettings;

//import com.nexeyo.erp.AccFinanceAccounts.AccFinanceAccounts;
//import com.nexeyo.erp.AccFinanceAccounts.AccFinanceAccountsRepo;
//import com.nexeyo.erp.AccFinanceCategory.AccFinanceCategoryRepo;
//import com.nexeyo.erp.AccFiscalYear.AccFiscalYear;
//import com.nexeyo.erp.AccFiscalYear.AccFiscalYearRepo;
//import com.nexeyo.erp.Currency.Currency;
//import com.nexeyo.erp.Currency.CurrencyRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SystemSettingsService {
    @Autowired
    SystemSettingsRepo systemSettingsRepo;
//    @Autowired
//    private CurrencyRepo currencyRepo;
//    @Autowired
//    private AccFinanceCategoryRepo accFinanceCategoryRepo;
//    @Autowired
//    private AccFinanceAccountsRepo accFinanceAccountsRepo;
//    @Autowired
//    private AccFiscalYearRepo accFiscalYearRepo;

    Logger logger = LoggerFactory.getLogger(SystemSettingsService.class);

    public ResponseEntity<?> SetCompanyVatPayable(int id) {
        SystemSettings systemSettings = new SystemSettings();
        systemSettings.setId(13);
        systemSettings.setField("company-vat-payable_account_id");
        systemSettings.setField_value(String.valueOf(id));
        return ResponseEntity.ok(systemSettingsRepo.save(systemSettings));
    }

    public ResponseEntity<?> SetCompanyVatReceivable(int id) {
        SystemSettings systemSettings = new SystemSettings();
        systemSettings.setId(14);
        systemSettings.setField("company-vat-receivable_account_id");
        systemSettings.setField_value(String.valueOf(id));
        return ResponseEntity.ok(systemSettingsRepo.save(systemSettings));
    }

//    public ResponseEntity<?> SetSystemCurrency(int id) {
//        logger.info("Attempting to set system currency with ID: {}", id);
//        Optional<Currency> currencyOptional = currencyRepo.findById(id);
//        if (currencyOptional.isEmpty()) {
//            logger.error("Currency with ID {} not found", id);
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid currency id !");
//        } else {
//            Optional<SystemSettings> systemSettingsOptional = systemSettingsRepo.findByFieldIgnoreCase("system-currency");
//            if (systemSettingsOptional.isEmpty()) {
//                logger.info("No existing system currency found, creating new entry for system-currency");
//                SystemSettings systemSettings = new SystemSettings();
//                systemSettings.setField("system-currency");
//                systemSettings.setField_value(String.valueOf(id));
//                logger.info("New system currency saving");
//                return ResponseEntity.ok(systemSettingsRepo.save(systemSettings));
//            } else {
//                logger.info("Updating existing system currency with ID: {}", id);
//                systemSettingsOptional.get().setField_value(String.valueOf(id));
//                logger.info("Updating system currency");
//                return ResponseEntity.ok(systemSettingsRepo.save(systemSettingsOptional.get()));
//            }
//        }
//    }

//    public ResponseEntity<?> GetSystemCurrency() {
//        Optional<SystemSettings> systemSettingsOptional = systemSettingsRepo.findByFieldIgnoreCase("system-currency");
//        if (systemSettingsOptional.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Currency not found !");
//        } else {
//            return ResponseEntity.ok(currencyRepo.findById(Integer.valueOf(systemSettingsOptional.get().getField_value())));
//        }
//    }

    public ResponseEntity<?> setCostUpdateStartCharacter(String character) {
        logger.info("Attempting to set 'cost-update-start-character' with value: {}", character);
        SystemSettings savedSystemSettings = new SystemSettings();
        Optional<SystemSettings> numberOptional = systemSettingsRepo.findByFieldIgnoreCase("cost-update-start-character");
        if (numberOptional.isEmpty()) {
            logger.info("No existing 'cost-update-start-character' found, creating new entry with value: {}", character);
            SystemSettings systemSettings = new SystemSettings();
            systemSettings.setField("cost-update-start-character");
            systemSettings.setField_value(character);
            savedSystemSettings = systemSettingsRepo.save(systemSettings);
            logger.info("New 'cost-update-start-character' saved: {}", savedSystemSettings);
        } else {
            logger.info("Updating existing 'cost-update-start-character' with value: {}", character);
            numberOptional.get().setField("cost-update-start-character");
            numberOptional.get().setField_value(character);
            savedSystemSettings = systemSettingsRepo.save(numberOptional.get());
            logger.info("Updated 'cost-update-start-character': {}", savedSystemSettings);
        }
        return ResponseEntity.ok(savedSystemSettings);
    }

    public ResponseEntity<?> setCostUpdateStartNumber(String number) {
        logger.info("Attempting to set 'cost-update-start-number' with value: {}", number);
        SystemSettings savedSystemSettings = new SystemSettings();
        Optional<SystemSettings> characterOptional = systemSettingsRepo.findByFieldIgnoreCase("cost-update-start-number");
        if (characterOptional.isEmpty()) {
            logger.info("No existing 'cost-update-start-number' found, creating new entry with value: {}", number);
            SystemSettings systemSettings = new SystemSettings();
            systemSettings.setField("cost-update-start-number");
            systemSettings.setField_value(number);
            savedSystemSettings = systemSettingsRepo.save(systemSettings);
            logger.info("New 'cost-update-start-number' saved: {}", savedSystemSettings);
        } else {
            logger.info("Updating existing 'cost-update-start-number' with value: {}", number);
            characterOptional.get().setField("cost-update-start-number");
            characterOptional.get().setField_value(number);
            savedSystemSettings = systemSettingsRepo.save(characterOptional.get());
            logger.info("Updated 'cost-update-start-number': {}", savedSystemSettings);
        }
        return ResponseEntity.ok(savedSystemSettings);
    }

    public ResponseEntity<?> getCostUpdateStartNumber() {
        Optional<SystemSettings> numberOptional = systemSettingsRepo.findByFieldIgnoreCase("cost-update-start-number");
        if (numberOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please set start number !");
        } else {
            return ResponseEntity.ok(numberOptional);
        }
    }

    public ResponseEntity<?> getCostUpdateStartCharacter() {
        Optional<SystemSettings> numberOptional = systemSettingsRepo.findByFieldIgnoreCase("cost-update-start-character");
        if (numberOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please set start number !");
        } else {
            return ResponseEntity.ok(numberOptional);
        }
    }

    public ResponseEntity<?> setBalanceSheetGroupType(String type) {
        logger.info("Attempting to set 'balance-sheet-group-type' with value: {}", type);
        SystemSettings savedSystemSettings = new SystemSettings();
        Optional<SystemSettings> balanceSheet = systemSettingsRepo.findByFieldIgnoreCase("balance-sheet-group-type");
        if (balanceSheet.isEmpty()) {
            logger.info("No existing 'balance-sheet-group-type' found, creating new entry with value: {}", type);
            SystemSettings systemSettings = new SystemSettings();
            systemSettings.setField("balance-sheet-group-type");
            systemSettings.setField_value(type);
            savedSystemSettings = systemSettingsRepo.save(systemSettings);
            logger.info("New 'balance-sheet-group-type' saved: {}", savedSystemSettings);
        } else {
            logger.info("Updating existing 'balance-sheet-group-type' with value: {}", type);
            balanceSheet.get().setField("balance-sheet-group-type");
            balanceSheet.get().setField_value(type);
            savedSystemSettings = systemSettingsRepo.save(balanceSheet.get());
            logger.info("Updated 'balance-sheet-group-type': {}", savedSystemSettings);
        }
        return ResponseEntity.ok(savedSystemSettings);
    }

    public ResponseEntity<?> getBalanceSheetGroupType() {
        Optional<SystemSettings> numberOptional = systemSettingsRepo.findByFieldIgnoreCase("balance-sheet-group-type");
        if (numberOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please set balance sheet type id first");
        } else {
            return ResponseEntity.ok(numberOptional);
        }
    }

    public ResponseEntity<?> getPNL() {
        Optional<SystemSettings> numberOptional = systemSettingsRepo.findByFieldIgnoreCase("pnl-group-type");
        if (numberOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please set pnl type id first");
        } else {
            return ResponseEntity.ok(numberOptional);
        }
    }

    public ResponseEntity<?> setPNL(String type) {
        logger.info("Attempting to set 'pnl-group-type' with value: {}", type);

        SystemSettings savedSystemSettings = new SystemSettings();
        Optional<SystemSettings> balanceSheet = systemSettingsRepo.findByFieldIgnoreCase("pnl-group-type");
        if (balanceSheet.isEmpty()) {
            logger.info("No existing 'pnl-group-type' found, creating new entry with value: {}", type);
            SystemSettings systemSettings = new SystemSettings();
            systemSettings.setField("pnl-group-type");
            systemSettings.setField_value(type);
            savedSystemSettings = systemSettingsRepo.save(systemSettings);
            logger.info("New 'pnl-group-type' saved: {}", savedSystemSettings);
        } else {
            logger.info("Updating existing 'pnl-group-type' with value: {}", type);
            balanceSheet.get().setField("pnl-group-type");
            balanceSheet.get().setField_value(type);
            savedSystemSettings = systemSettingsRepo.save(balanceSheet.get());
            logger.info("Updated 'pnl-group-type': {}", savedSystemSettings);
        }
        return ResponseEntity.ok(savedSystemSettings);
    }

//    public ResponseEntity<?> setInventorySuspend(String id) {
//        logger.info("Attempting to set 'inventory-suspend-id' with value: {}", id);
//        SystemSettings savedSystemSettings = new SystemSettings();
//        Optional<AccFinanceAccounts> accFinanceCategoryOptional = accFinanceAccountsRepo.findById(Integer.valueOf(id));
//        if (accFinanceCategoryOptional.isEmpty()) {
//            logger.error("Invalid account id: {}", id);
//            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Invalid account id !");
//        }
//        Optional<SystemSettings> balanceSheet = systemSettingsRepo.findByFieldIgnoreCase("inventory-suspend-id");
//        if (balanceSheet.isEmpty()) {
//            logger.info("No existing 'inventory-suspend-id' found, creating new entry with value: {}", id);
//            SystemSettings systemSettings = new SystemSettings();
//            systemSettings.setField("inventory-suspend-id");
//            systemSettings.setField_value(id);
//            savedSystemSettings = systemSettingsRepo.save(systemSettings);
//            logger.info("New 'inventory-suspend-id' saved: {}", savedSystemSettings);
//        } else {
//            logger.info("Updating existing 'inventory-suspend-id' with value: {}", id);
//            balanceSheet.get().setField("inventory-suspend-id");
//            balanceSheet.get().setField_value(id);
//            savedSystemSettings = systemSettingsRepo.save(balanceSheet.get());
//            logger.info("Updated 'inventory-suspend-id': {}", savedSystemSettings);
//        }
//        return ResponseEntity.ok(savedSystemSettings);
//    }

//    public ResponseEntity<?> getInventorySuspend() {
//        Optional<SystemSettings> numberOptional = systemSettingsRepo.findByFieldIgnoreCase("inventory-suspend-id");
//        if (numberOptional.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please set inventory suspend id first");
//        } else {
//            Optional<AccFinanceAccounts> accFinanceAccountsOptional = accFinanceAccountsRepo.findById(Integer.valueOf(numberOptional.get().getId()));
//            numberOptional.get().setContent(accFinanceAccountsOptional);
//            return ResponseEntity.ok(numberOptional);
//        }
//    }

    public ResponseEntity<?> setCommonCashReceipt(String name) {
        logger.info("Request to set common cash receipt with name: {}", name);
        Optional<SystemSettings> commonReceipt = systemSettingsRepo.findByFieldIgnoreCase("common-cash-receipt");
        if (commonReceipt.isEmpty()) {
            logger.info("No existing 'common-cash-receipt' found, creating new entry with value: {}", name);
            SystemSettings systemSettings = new SystemSettings();
            systemSettings.setField("common-cash-receipt");
            systemSettings.setField_value(name);
            systemSettingsRepo.save(systemSettings);
            logger.info("New 'common-cash-receipt' saved with value: {}", name);
        } else {
            logger.info("Updating existing 'common-cash-receipt' with value: {}", name);
            commonReceipt.get().setField("common-cash-receipt");
            commonReceipt.get().setField_value(name);
            systemSettingsRepo.save(commonReceipt.get());
            logger.info("Updated 'common-cash-receipt' with value: {}", name);
        }
        return ResponseEntity.ok("success !");
    }

//    public ResponseEntity<?> getCommonCashReceipt() {
//        logger.info("Request to get common cash receipt");
//        Optional<SystemSettings> numberOptional = systemSettingsRepo.findByFieldIgnoreCase("common-cash-receipt");
//        if (numberOptional.isEmpty()) {
//            logger.error("Common cash receipt not found");
//            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please add common-cash-receipt first");
//        } else {
//            Optional<AccFinanceAccounts> accFinanceAccountsOptional = accFinanceAccountsRepo.findById(Integer.valueOf(numberOptional.get().getId()));
//            numberOptional.get().setContent(accFinanceAccountsOptional);
//            logger.info("Returning common cash receipt: {}", numberOptional.get());
//            return ResponseEntity.ok(numberOptional);
//        }
//    }

    public ResponseEntity<?> setCommonBankReceipt(String name) {
        logger.info("Request to set common bank receipt with name: {}", name);
        Optional<SystemSettings> commonReceipt = systemSettingsRepo.findByFieldIgnoreCase("common-bank-receipt");
        if (commonReceipt.isEmpty()) {
            logger.info("No existing 'common-bank-receipt' found, creating new entry with value: {}", name);
            SystemSettings systemSettings = new SystemSettings();
            systemSettings.setField("common-bank-receipt");
            systemSettings.setField_value(name);
            systemSettingsRepo.save(systemSettings);
            logger.info("New 'common-bank-receipt' saved with value: {}", name);
        } else {
            logger.info("Updating existing 'common-bank-receipt' with value: {}", name);
            commonReceipt.get().setField("common-bank-receipt");
            commonReceipt.get().setField_value(name);
            systemSettingsRepo.save(commonReceipt.get());
            logger.info("Updated 'common-bank-receipt' with value: {}", name);
        }
        return ResponseEntity.ok("success !");
    }

//    public ResponseEntity<?> getCommonBankReceipt() {
//        logger.info("Request to get common bank receipt");
//        Optional<SystemSettings> numberOptional = systemSettingsRepo.findByFieldIgnoreCase("common-bank-receipt");
//        if (numberOptional.isEmpty()) {
//            logger.error("Common bank receipt not found");
//            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please add common-cash-receipt first");
//        } else {
//            Optional<AccFinanceAccounts> accFinanceAccountsOptional = accFinanceAccountsRepo.findById(Integer.valueOf(numberOptional.get().getId()));
//            numberOptional.get().setContent(accFinanceAccountsOptional);
//            logger.info("Returning common bank receipt: {}", numberOptional.get());
//            return ResponseEntity.ok(numberOptional);
//        }
//    }

//    public ResponseEntity<?> getVendorPayableControllerAccount() {
//        logger.info("Request to get vendor payable controller account");
//        Optional<SystemSettings> numberOptional = systemSettingsRepo.findByFieldIgnoreCase("vendor-payable-controller-account");
//        if (numberOptional.isEmpty()) {
//            logger.error("Vendor payable controller account not found");
//            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please add vendor-payable-controller-account first");
//        } else {
//            Optional<AccFinanceAccounts> accFinanceAccountsOptional = accFinanceAccountsRepo.findById(Integer.valueOf(numberOptional.get().getId()));
//            numberOptional.get().setContent(accFinanceAccountsOptional);
//            logger.info("Returning vendor payable controller account: {}", numberOptional.get());
//            return ResponseEntity.ok(numberOptional);
//        }
//    }

    public ResponseEntity<?> setVendorPayableControllerAccount(String name) {
        logger.info("Request to set vendor payable controller account with name: {}", name);
        Optional<SystemSettings> commonReceipt = systemSettingsRepo.findByFieldIgnoreCase("vendor-payable-controller-account");
        if (commonReceipt.isEmpty()) {
            logger.info("No existing 'vendor-payable-controller-account' found, creating new entry with value: {}", name);
            SystemSettings systemSettings = new SystemSettings();
            systemSettings.setField("vendor-payable-controller-account");
            systemSettings.setField_value(name);
            systemSettingsRepo.save(systemSettings);
            logger.info("New 'vendor-payable-controller-account' saved with value: {}", name);
        } else {
            logger.info("Updating existing 'vendor-payable-controller-account' with value: {}", name);
            commonReceipt.get().setField("vendor-payable-controller-account");
            commonReceipt.get().setField_value(name);
            systemSettingsRepo.save(commonReceipt.get());
            logger.info("Updated 'vendor-payable-controller-account' with value: {}", name);
        }
        return ResponseEntity.ok("success !");
    }

//    public ResponseEntity<?> getVendorReceivableControllerAccount() {
//        logger.info("Request to get vendor receivable controller account");
//        Optional<SystemSettings> numberOptional = systemSettingsRepo.findByFieldIgnoreCase("vendor-receivable-controller-account");
//        if (numberOptional.isEmpty()) {
//            logger.error("Vendor receivable controller account not found");
//            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please add vendor-receivable-controller-account first");
//        } else {
//            Optional<AccFinanceAccounts> accFinanceAccountsOptional = accFinanceAccountsRepo.findById(Integer.valueOf(numberOptional.get().getId()));
//            numberOptional.get().setContent(accFinanceAccountsOptional);
//            logger.info("Returning vendor receivable controller account: {}", numberOptional.get());
//            return ResponseEntity.ok(numberOptional);
//        }
//    }

    public ResponseEntity<?> setVendorReceivableControllerAccount(String name) {
        logger.info("Request to set vendor receivable controller account with name: {}", name);
        Optional<SystemSettings> commonReceipt = systemSettingsRepo.findByFieldIgnoreCase("vendor-receivable-controller-account");
        if (commonReceipt.isEmpty()) {
            logger.info("No existing 'vendor-receivable-controller-account' found, creating new entry with value: {}", name);
            SystemSettings systemSettings = new SystemSettings();
            systemSettings.setField("vendor-receivable-controller-account");
            systemSettings.setField_value(name);
            systemSettingsRepo.save(systemSettings);
            logger.info("New 'vendor-receivable-controller-account' saved with value: {}", name);
        } else {
            logger.info("Updating existing 'vendor-receivable-controller-account' with value: {}", name);
            commonReceipt.get().setField("vendor-receivable-controller-account");
            commonReceipt.get().setField_value(name);
            systemSettingsRepo.save(commonReceipt.get());
            logger.info("Updated 'vendor-receivable-controller-account' with value: {}", name);
        }
        return ResponseEntity.ok("success !");
    }

//    public ResponseEntity<?> getCurrentFiscalYear() {
//        logger.info("Request to get current fiscal year");
//        Optional<SystemSettings> fiscalYear = systemSettingsRepo.findByFieldIgnoreCase("fiscal-year");
//        if (fiscalYear.isPresent()) {
//            Optional<AccFiscalYear> fiscalYearOptional = accFiscalYearRepo.findById(Integer.valueOf(fiscalYear.get().getField_value()));
//            if (fiscalYearOptional.isPresent()) {
//                logger.info("Returning current fiscal year: {}", fiscalYearOptional.get());
//                return ResponseEntity.ok(fiscalYearOptional);
//            } else {
//                logger.error("Invalid fiscal year");
//                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Invalid Fiscal Year !");
//            }
//        } else {
//            logger.error("Fiscal year not defined");
//            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please define fiscal Year !");
//        }
//    }

//    public ResponseEntity<?> setCurrentFiscalYear(int fiscalYearId) {
//        logger.info("Request to set current fiscal year with ID: {}", fiscalYearId);
//        Optional<AccFiscalYear> fiscalYearOptional = accFiscalYearRepo.findById(fiscalYearId);
//        if (fiscalYearOptional.isPresent()) {
//            Optional<SystemSettings> fiscalYear = systemSettingsRepo.findByFieldIgnoreCase("fiscal-year");
//            if (fiscalYear.isPresent()) {
//                fiscalYear.get().setField_value(String.valueOf(fiscalYearId));
//                logger.info("Updating fiscal year with ID: {}", fiscalYearId);
//                return ResponseEntity.ok(systemSettingsRepo.save(fiscalYear.get()));
//
//            } else {
//                logger.info("Creating new fiscal year entry with ID: {}", fiscalYearId);
//                SystemSettings systemSettings = new SystemSettings();
//                systemSettings.setField_value(String.valueOf(fiscalYearId));
//                systemSettings.setField("fiscal-year");
//                return ResponseEntity.ok(systemSettingsRepo.save(systemSettings));
//
//            }
//        } else {
//            logger.error("Invalid fiscal year ID: {}", fiscalYearId);
//            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Invalid fiscal year id !");
//        }
//
//    }

    public ResponseEntity<?> setFiscalYearStartPeriod(int fiscalYearStartMonth) {
        logger.info("Request to set fiscal year start month with value: {}", fiscalYearStartMonth);
        SystemSettings savedSystemSettings = new SystemSettings();
        Optional<SystemSettings> fiscalYearStartMonthOptional = systemSettingsRepo.findByFieldIgnoreCase("fiscal-year-start-month");
        if (fiscalYearStartMonthOptional.isEmpty()) {
            logger.info("No existing 'fiscal-year-start-month' found, creating new entry with value: {}", fiscalYearStartMonth);
            SystemSettings systemSettings = new SystemSettings();
            systemSettings.setField("fiscal-year-start-month");
            systemSettings.setField_value(String.valueOf(fiscalYearStartMonth));
            savedSystemSettings = systemSettingsRepo.save(systemSettings);
            logger.info("New 'fiscal-year-start-month' saved: {}", savedSystemSettings);
        } else {
            logger.info("Updating existing 'fiscal-year-start-month' with value: {}", fiscalYearStartMonth);
            fiscalYearStartMonthOptional.get().setField("fiscal-year-start-month");
            fiscalYearStartMonthOptional.get().setField_value(String.valueOf(fiscalYearStartMonth));
            savedSystemSettings = systemSettingsRepo.save(fiscalYearStartMonthOptional.get());
            logger.info("Updated 'fiscal-year-start-month': {}", savedSystemSettings);
        }
        return ResponseEntity.ok(savedSystemSettings);
    }
}
