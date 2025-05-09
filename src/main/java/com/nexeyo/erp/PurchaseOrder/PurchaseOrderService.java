package com.nexeyo.erp.PurchaseOrder;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nexeyo.erp.CompanyAddresss.CompanyAddress;
import com.nexeyo.erp.CompanyAddresss.CompanyAddressRepo;
import com.nexeyo.erp.Config.ConfigRepo;
//import com.nexeyo.erp.CurrentCurrency.CurrentCurrency;
//import com.nexeyo.erp.CurrentCurrency.CurrentCurrencyRepo;
import com.nexeyo.erp.DeliveryTerms.DeliveryTermsRepo;
//import com.nexeyo.erp.Email.EmailConnection;
import com.nexeyo.erp.Incoterms.IncotermsRepo;
//import com.nexeyo.erp.Item.Item;
//import com.nexeyo.erp.Item.ItemRepo;
import com.nexeyo.erp.Location.Location;
import com.nexeyo.erp.Location.LocationRepo;
import com.nexeyo.erp.NumberingSystem.NumberingSystem;
import com.nexeyo.erp.NumberingSystem.NumberingSystemRepo;
import com.nexeyo.erp.PaymentTerms.PaymentTermsRepo;
import com.nexeyo.erp.PaymentTypes.PaymentTypesRepo;
//import com.nexeyo.erp.PoQueue.PoDto.PoTaskDetail;
//import com.nexeyo.erp.PoQueue.Service.TaskExecutionService;
import com.nexeyo.erp.PurchaseOrderItems.PurchaseOrderItem;
import com.nexeyo.erp.PurchaseOrderItems.PurchaseOrderItemRepo;
//import com.nexeyo.erp.ServicePurchaseOrder.ServicePurchaseOrderRepo;
import com.nexeyo.erp.Supplier.Supplier;
import com.nexeyo.erp.Supplier.SupplierRepo;
import com.nexeyo.erp.SupplierAddress.SupplierAddress;
import com.nexeyo.erp.SupplierAddress.SupplierAddressRepo;
//import com.nexeyo.erp.SystemSettings.SystemSettings;
//import com.nexeyo.erp.SystemSettings.SystemSettingsRepo;
import com.nexeyo.erp.UnitOfMeasurement.UnitOfMeasurementRepo;
//import com.nexeyo.erp.jwt.models.User;
//import com.nexeyo.erp.jwt.repository.UserRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;

@Service
@Component
public class PurchaseOrderService {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    Logger logger = LoggerFactory.getLogger(PurchaseOrderService.class);

    @Autowired
    PurchaseOrderRepo purchaseOrderRepo;
    @Autowired
    PurchaseOrderItemRepo purchaseOrderItemRepo;
//    @Autowired
//    ItemRepo itemRepo;

//    @Autowired
//    SystemSettingsRepo systemSettingsRepo;
    @Autowired
    private SupplierRepo supplierRepo;
    @Autowired
    private CompanyAddressRepo companyAddressRepo;
    @Autowired
    private SupplierAddressRepo supplierAddressRepo;
    @Autowired
    private LocationRepo locationRepo;
    @Autowired
    private ConfigRepo configRepo;
//    @Autowired
//    private TaskExecutionService taskExecutionService;
    @Autowired
    private PaymentTermsRepo paymentTermsRepo;
    @Autowired
    private DeliveryTermsRepo deliveryTermsRepo;
    @Autowired
    private PaymentTypesRepo paymentTypesRepo;
//    @Autowired
//    private UserRepository userRepository;
    @Autowired
    private UnitOfMeasurementRepo unitOfMeasurementRepo;
    @Autowired
    private IncotermsRepo incotermsRepo;
//    @Autowired
//    private CurrentCurrencyRepo currentCurrencyRepo;
//    @Autowired
//    private ServicePurchaseOrderRepo servicePurchaseOrderRepo;
    @Autowired
    private NumberingSystemRepo numberingSystemRepo;

    public ResponseEntity<?> Add(PurchaseOrder purchaseOrder) {
        purchaseOrder.setBack_order_status(1);
//        #Fire Validation

//        List<CurrentCurrency> currentCurrencyList = currentCurrencyRepo.findByCurrency_header_idOrderByCreate_atDesc(purchaseOrder.getCurrency_rate_header_id());
//        if (currentCurrencyList.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Invalid currency !");
//        }
        if (purchaseOrder.getLocation_id() == null || purchaseOrder.getLocation_id() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Invalid location_id");
        } else {
            if (!locationRepo.findById(purchaseOrder.getLocation_id()).isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please send correct location_id");
            }
        }
        logger.info("Add " + purchaseOrder.toString());
        if (purchaseOrder.getPurchaseOrderItemsList() == null || purchaseOrder.getPurchaseOrderItemsList().size() == 0) {
            logger.error("Purchase order can't be null !" + purchaseOrder.toString());
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Purchase order can't be null !");
        } else {
//            for (PurchaseOrderItem order :
//                    purchaseOrder.getPurchaseOrderItemsList()) {
//                if (order.getItem_id() == null || order.getItem_id() == 0) {
//                    logger.error("Purchase order item id can't be null !");
//                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Purchase order item id can't be null !");
//                } else {
//                    if (!itemRepo.findById(order.getItem_id()).isPresent()) {
//                        logger.info("Invalid purchase order item id !");
//                        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Invalid purchase order item id !");
//                    }
//                }
//            }

            if (purchaseOrder.getSupplier_id() == null || purchaseOrder.getSupplier_id() == 0) {
                logger.info("supplier id can't be null !");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("supplier id can't be null !");
            } else {
                if (!supplierRepo.findById(purchaseOrder.getSupplier_id()).isPresent()) {
                    logger.error("Invalid supplier id !");
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Invalid supplier id !");
                }
            }

            if (purchaseOrder.getDelivery_address_from_id() == null || purchaseOrder.getDelivery_address_from_id() == 0) {
                logger.error("supplier address from id can't be null !");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("supplier address from id can't be null !");
            } else {
                if (companyAddressRepo.findById(purchaseOrder.getDelivery_address_from_id()).isEmpty()) {
                    logger.error("Invalid supplier address from id !");
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Invalid supplier address from id !");
                }
            }
            if (purchaseOrder.getSupplier_address_id() == null || purchaseOrder.getSupplier_address_id() == 0) {
                logger.error("supplier address id can't be null !");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("supplier address id can't be null !");
            } else {
                if (!supplierAddressRepo.findById(purchaseOrder.getSupplier_address_id()).isPresent()) {
                    logger.info("Invalid supplier address id !");
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Invalid supplier address id !");
                }
            }


        }

        Optional<NumberingSystem> numberingSystem = numberingSystemRepo.findByTypeName("purchase-order");
        if (numberingSystem.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please set Numbering system, purchase-order");
        }

//        set currency
//        Optional<SystemSettings> systemSettingsOptional = systemSettingsRepo.findByFieldIgnoreCase("system-currency");
//        List<CurrentCurrency> systemCurrentCurrencyList = currentCurrencyRepo.findByCurrency_header_idOrderByCreate_atDesc(Integer.valueOf(systemSettingsOptional.get().getField_value()));


//        if (systemSettingsOptional.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("System currency not found !");
//        } else {
//            purchaseOrder.setSystem_currency_rate_id(systemCurrentCurrencyList.get(0).getId());
//        }
//
//        purchaseOrder.setCurrency_rate_id(currentCurrencyList.get(0).getId());


        PurchaseOrder purchaseOrderSaved = new PurchaseOrder();

        try {
            int trying = 0;
            List<PurchaseOrderItem> purchaseOrderItem = purchaseOrder.getPurchaseOrderItemsList();
            purchaseOrder.setPurchaseOrderItemsList(null);
            purchaseOrder.setSuppliers(null);
            purchaseOrder.setSupplierSelectedAddress(null);

//            String character = systemSettingsRepo.findByFieldIgnoreCase("po_character").get().getField_value();
//            int poStartFrom = Integer.parseInt(systemSettingsRepo.findByFieldIgnoreCase("po_start_from").get().getField_value());
//            boolean check = true;

            try {
                Integer lastNo = numberingSystem.get().getLastNo();
                String charr = numberingSystem.get().getStartCharacter();
                for (int i = 0; i < 5; i++) {
                    try {
                        lastNo++;
                        purchaseOrder.setPo_no(charr + lastNo);
                        purchaseOrder.setPo_no_without_characters(lastNo);
                        purchaseOrderSaved = purchaseOrderRepo.save(purchaseOrder);
                        numberingSystem.get().setLastNo(lastNo);
                        numberingSystem.get().setLastDocumentNo(purchaseOrder.getId());
                        numberingSystemRepo.save(numberingSystem.get());
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (i >= 4) {
                            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Something went wrong !");
                        }
                    }
                }

            } catch (Exception e) {
            }
//            Integer last_id = purchaseOrderRepo.geMaxValue();
//            Integer lastServicePo_id = servicePurchaseOrderRepo.geMaxValue();
//
//            if (last_id > lastServicePo_id){
//                if (last_id == 0) {
//                    last_id = poStartFrom;
//                }
//                while (check) {
//                    try {
//                        if (last_id == 0) {
//                            purchaseOrder.setPo_no(character + last_id);
//                        } else {
//                            last_id++;
//                            purchaseOrder.setPo_no(character + last_id);
//                        }
//                        check = false;
//                        purchaseOrder.setPo_no_without_characters(last_id);
//                        purchaseOrder.setCreate_at(LocalDateTime.now());
//                        purchaseOrderSaved = purchaseOrderRepo.save(purchaseOrder);
//                    } catch (Exception e) {
//                        last_id++;
//                        trying++;
//                        if (trying == 5) {
//                            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please check body");
//                        }
//                    }
//                }
//            }else {
//                if (lastServicePo_id == 0) {
//                    lastServicePo_id = poStartFrom;
//                }
//                while (check) {
//                    try {
//                        if (lastServicePo_id == 0) {
//                            purchaseOrder.setPo_no(character + lastServicePo_id);
//                        } else {
//                            lastServicePo_id++;
//                            purchaseOrder.setPo_no(character + lastServicePo_id);
//                        }
//                        check = false;
//                        purchaseOrder.setPo_no_without_characters(lastServicePo_id);
//                        purchaseOrder.setCreate_at(LocalDateTime.now());
//                        purchaseOrderSaved = purchaseOrderRepo.save(purchaseOrder);
//                    } catch (Exception e) {
//                        lastServicePo_id++;
//                        trying++;
//                        if (trying == 5) {
//                            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please check body");
//                        }
//                    }
//                }
//            }



//            old

//            if (last_id == null) {
//                last_id = poStartFrom;
//            }
//            while (check) {
//                try {
//                    if (last_id == 0) {
//                        purchaseOrder.setPo_no(character + last_id);
//                    } else {
//                        last_id++;
//                        purchaseOrder.setPo_no(character + last_id);
//                    }
//                    check = false;
//                    purchaseOrder.setPo_no_without_characters(last_id);
//                    purchaseOrder.setCreate_at(LocalDateTime.now());
//                    purchaseOrderSaved = purchaseOrderRepo.save(purchaseOrder);
//                } catch (Exception e) {
//                    last_id++;
//                    trying++;
//                    if (trying == 5) {
//                        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please check body");
//                    }
//                }
//            }

//            double total_cost_amount = 0;
//            double total_discount_amount = 0;
//            double total_amount = 0;
//            double tax_cal = 0;
//            double tax_cal_amount = 0;
//            double qty_cal = 0;
//            double discount_cal = 0;
//            double cost_price_cal = 0;
//            purchaseOrderSaved = purchaseOrderRepo.save(purchaseOrder);

            for (int i = 0; i < purchaseOrderItem.size(); i++) {
                purchaseOrderItem.get(i).setOrder_id(purchaseOrderSaved.getId());
                purchaseOrderItem.get(i).setRemaining_quantity(purchaseOrderItem.get(i).getQuantity());
                purchaseOrderItem.get(i).setReceived_quantity(0.0);
                purchaseOrderItem.get(i).setStatus(1);
//                List<MaterialMasterItemWise> materialMasterItemWiseList = itemRepo.findByItem_id(purchaseOrderItem.get(i).getItem_id()).get().getFields();
//                for (int j = 0; j < materialMasterItemWiseList.size(); j++) {
////                    qty
//                    if (materialMasterItemWiseList.get(i).getField_id().equalsIgnoreCase("45")) {
//                        qty_cal = Double.parseDouble(materialMasterItemWiseList.get(i).getValues().get(0).getValue());
//                    }
////                    purchase price
//                    if (materialMasterItemWiseList.get(i).getField_id().equalsIgnoreCase("46")) {
//                        cost_price_cal += Double.parseDouble(materialMasterItemWiseList.get(i).getValues().get(0).getValue());
//                    }
////                    vendor tax
//                    if (materialMasterItemWiseList.get(i).getField_id().equalsIgnoreCase("42")) {
//                        tax_cal += Double.parseDouble(materialMasterItemWiseList.get(i).getValues().get(0).getValue());
//                    }
////                    discount
//                    if (materialMasterItemWiseList.get(i).getField_id().equalsIgnoreCase("48")) {
//                        discount_cal += Double.parseDouble(materialMasterItemWiseList.get(i).getValues().get(0).getValue());
//                    }
//
//                }
//                purchaseOrderItem.get(i).setCost_price(String.valueOf(cost_price_cal * qty_cal));
//                purchaseOrderItem.get(i).setDiscount(String.valueOf(discount_cal * qty_cal));
//                purchaseOrderItem.get(i).setAmount(String.valueOf((cost_price_cal * qty_cal) - (discount_cal * qty_cal)));
//                purchaseOrderItem.get(i).setTax_rate(tax_cal);
//                purchaseOrderItem.get(i).setTax_amount((((cost_price_cal - discount_cal) * qty_cal) * tax_cal) / 100);
//
//
//                total_cost_amount += (cost_price_cal * qty_cal) - (discount_cal * qty_cal);
//                total_discount_amount += (discount_cal * qty_cal);
//                tax_cal_amount += total_cost_amount * tax_cal;

            }
//            total_amount = total_cost_amount + tax_cal_amount;
//            purchaseOrderSaved.setPurchaseOrderItemsList(null);
//            purchaseOrderSaved.setTotal_discount(total_discount_amount);
//            purchaseOrderSaved.setSub_total(total_cost_amount);
//            purchaseOrderSaved.setTotal(total_amount);


            purchaseOrderRepo.save(purchaseOrderSaved);
            purchaseOrderItemRepo.saveAll(purchaseOrderItem);
            logger.info("PO SAVED");
        } catch (Exception e) {
            logger.info("Your request not completed. Please contact development team ! \n Error :" + e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Your request not completed. Please contact development team ! \n Error :" + e);
        }

//        try {
////            GeneratePOReport(purchaseOrderSaved.getId());
//            PoTaskDetail poTaskDetail = new PoTaskDetail();
//            poTaskDetail.setPo(purchaseOrderSaved.getId());
//            taskExecutionService.submitTaskInQueue(poTaskDetail);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        return ResponseEntity.ok(purchaseOrderRepo.findById(purchaseOrderSaved.getId()));
    }

    public PurchaseOrder DirectAdd(PurchaseOrder purchaseOrder) {
        PurchaseOrder purchaseOrderSaved1 = new PurchaseOrder();

        purchaseOrder.setBack_order_status(1);
//        #Fire Validation

        Optional<NumberingSystem> numberingSystem = numberingSystemRepo.findByTypeName("purchase-order");
        if (numberingSystem.isEmpty()) {
            logger.error("Please set Numbering system, purchase-order");
            return null;
        }

        if (purchaseOrder.getLocation_id() == null || purchaseOrder.getLocation_id() == 0) {
            return null;
        } else {
            if (!locationRepo.findById(purchaseOrder.getLocation_id()).isPresent()) {
                return null;
            }
        }
        logger.info("Add " + purchaseOrder.toString());
        if (purchaseOrder.getPurchaseOrderItemsList() == null || purchaseOrder.getPurchaseOrderItemsList().size() == 0) {
            logger.error("Purchase order can't be null !" + purchaseOrder.toString());
            return null;
        } else {
//            for (PurchaseOrderItem order :
//                    purchaseOrder.getPurchaseOrderItemsList()) {
//                if (order.getItem_id() == null || order.getItem_id() == 0) {
//                    logger.error("Purchase order item id can't be null !");
//                    return null;
//                } else {
//                    if (!itemRepo.findById(order.getItem_id()).isPresent()) {
//                        logger.info("Invalid purchase order item id !");
//                        return null;
//                    }
//                }
//            }

            if (purchaseOrder.getSupplier_id() == null || purchaseOrder.getSupplier_id() == 0) {
                logger.info("supplier id can't be null !");
                return null;
            } else {
                if (!supplierRepo.findById(purchaseOrder.getSupplier_id()).isPresent()) {
                    logger.error("Invalid supplier id !");
                    return null;
                }
            }

            if (purchaseOrder.getDelivery_address_from_id() == null || purchaseOrder.getDelivery_address_from_id() == 0) {
                logger.error("supplier address from id can't be null !");
                return null;
            } else {
                if (!companyAddressRepo.findById(purchaseOrder.getDelivery_address_from_id()).isPresent()) {
                    logger.error("Invalid supplier address from id !");
                    return null;
                }
            }
            if (purchaseOrder.getSupplier_address_id() == null || purchaseOrder.getSupplier_address_id() == 0) {
                logger.error("supplier address id can't be null !");
                return null;
            } else {
                if (!supplierAddressRepo.findById(purchaseOrder.getSupplier_address_id()).isPresent()) {
                    logger.info("Invalid supplier address id !");
                    return null;
                }
            }


        }


        PurchaseOrder purchaseOrderSaved = new PurchaseOrder();

        try {
            int trying = 0;
            List<PurchaseOrderItem> purchaseOrderItem = purchaseOrder.getPurchaseOrderItemsList();
            purchaseOrder.setPurchaseOrderItemsList(null);
            purchaseOrder.setSuppliers(null);
            purchaseOrder.setSupplierSelectedAddress(null);

//            String character = systemSettingsRepo.findByFieldIgnoreCase("po_character").get().getField_value();
//            int poStartFrom = Integer.parseInt(systemSettingsRepo.findByFieldIgnoreCase("po_start_from").get().getField_value());
//            boolean check = true;
//            Integer last_id = purchaseOrderRepo.geMaxValue();
//            if (last_id == null) {
//                last_id = poStartFrom;
//            }
//            while (check) {
//                try {
//                    if (last_id == 0) {
//                        purchaseOrder.setPo_no(character + last_id);
//                    } else {
//                        last_id++;
//                        purchaseOrder.setPo_no(character + last_id);
//                    }
//                    check = false;
//                    purchaseOrder.setPo_no_without_characters(last_id);
//                    purchaseOrder.setCreate_at(LocalDateTime.now());
//                    purchaseOrderSaved = purchaseOrderRepo.save(purchaseOrder);
//                    purchaseOrderSaved1 = purchaseOrderSaved;
//                } catch (Exception e) {
//                    last_id++;
//                    trying++;
//                    if (trying == 5) {
//                        return null;
//                    }
//                }
//            }


            try {
                Integer lastNo = numberingSystem.get().getLastNo();
                String charr = numberingSystem.get().getStartCharacter();
                for (int i = 0; i < 5; i++) {
                    try {
                        lastNo++;
                        purchaseOrder.setPo_no(charr + lastNo);
                        purchaseOrder.setPo_no_without_characters(lastNo);
                        purchaseOrderSaved = purchaseOrderRepo.save(purchaseOrder);
                        numberingSystem.get().setLastNo(lastNo);
                        numberingSystem.get().setLastDocumentNo(purchaseOrder.getId());
                        numberingSystemRepo.save(numberingSystem.get());
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (i >= 4) {
                            logger.error("Something went wrong !");
                            return null;
                        }
                    }
                }

            } catch (Exception e) {
            }

//            double total_cost_amount = 0;
//            double total_discount_amount = 0;
//            double total_amount = 0;
//            double tax_cal = 0;
//            double tax_cal_amount = 0;
//            double qty_cal = 0;
//            double discount_cal = 0;
//            double cost_price_cal = 0;
//            purchaseOrderSaved = purchaseOrderRepo.save(purchaseOrder);

            for (int i = 0; i < purchaseOrderItem.size(); i++) {
                purchaseOrderItem.get(i).setOrder_id(purchaseOrderSaved.getId());
                purchaseOrderItem.get(i).setRemaining_quantity(purchaseOrderItem.get(i).getQuantity());
                purchaseOrderItem.get(i).setReceived_quantity(0.0);
                purchaseOrderItem.get(i).setStatus(1);
//                List<MaterialMasterItemWise> materialMasterItemWiseList = itemRepo.findByItem_id(purchaseOrderItem.get(i).getItem_id()).get().getFields();
//                for (int j = 0; j < materialMasterItemWiseList.size(); j++) {
////                    qty
//                    if (materialMasterItemWiseList.get(i).getField_id().equalsIgnoreCase("45")) {
//                        qty_cal = Double.parseDouble(materialMasterItemWiseList.get(i).getValues().get(0).getValue());
//                    }
////                    purchase price
//                    if (materialMasterItemWiseList.get(i).getField_id().equalsIgnoreCase("46")) {
//                        cost_price_cal += Double.parseDouble(materialMasterItemWiseList.get(i).getValues().get(0).getValue());
//                    }
////                    vendor tax
//                    if (materialMasterItemWiseList.get(i).getField_id().equalsIgnoreCase("42")) {
//                        tax_cal += Double.parseDouble(materialMasterItemWiseList.get(i).getValues().get(0).getValue());
//                    }
////                    discount
//                    if (materialMasterItemWiseList.get(i).getField_id().equalsIgnoreCase("48")) {
//                        discount_cal += Double.parseDouble(materialMasterItemWiseList.get(i).getValues().get(0).getValue());
//                    }
//
//                }
//                purchaseOrderItem.get(i).setCost_price(String.valueOf(cost_price_cal * qty_cal));
//                purchaseOrderItem.get(i).setDiscount(String.valueOf(discount_cal * qty_cal));
//                purchaseOrderItem.get(i).setAmount(String.valueOf((cost_price_cal * qty_cal) - (discount_cal * qty_cal)));
//                purchaseOrderItem.get(i).setTax_rate(tax_cal);
//                purchaseOrderItem.get(i).setTax_amount((((cost_price_cal - discount_cal) * qty_cal) * tax_cal) / 100);
//
//
//                total_cost_amount += (cost_price_cal * qty_cal) - (discount_cal * qty_cal);
//                total_discount_amount += (discount_cal * qty_cal);
//                tax_cal_amount += total_cost_amount * tax_cal;

            }
//            total_amount = total_cost_amount + tax_cal_amount;
//            purchaseOrderSaved.setPurchaseOrderItemsList(null);
//            purchaseOrderSaved.setTotal_discount(total_discount_amount);
//            purchaseOrderSaved.setSub_total(total_cost_amount);
//            purchaseOrderSaved.setTotal(total_amount);


            purchaseOrderRepo.save(purchaseOrderSaved);
            purchaseOrderItemRepo.saveAll(purchaseOrderItem);
            logger.info("PO SAVED");
        } catch (Exception e) {
            logger.info("Your request not completed. Please contact development team ! \n Error :" + e);
            return null;
        }

//        try {
////            GeneratePOReport(purchaseOrderSaved.getId());
//            PoTaskDetail poTaskDetail = new PoTaskDetail();
//            poTaskDetail.setPo(purchaseOrderSaved.getId());
//            taskExecutionService.submitTaskInQueue(poTaskDetail);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        return purchaseOrderSaved1;
    }

    public ResponseEntity<?> CancelOrder(int order_id) {
        try {
            Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepo.findById(order_id);
            if (purchaseOrder.isPresent()) {
                purchaseOrder.get().setCancelled(true);
            } else {
                logger.info("Order not found !");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found !");
            }
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            logger.info("Your request not completed. Please contact development team !");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Your request not completed. Please contact development team !");
        }

    }

//    public ResponseEntity<?> FindById(int id) {
//        Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepo.findById(id);
//        if (purchaseOrder.isPresent()) {
//            try {
//                Optional<PaymentTerms> paymentTermsOptional = paymentTermsRepo.findById(purchaseOrder.get().getPayment_terms());
//                if (paymentTermsOptional.isPresent()) {
//                    purchaseOrder.get().setPayment_terms_detail(paymentTermsOptional.get());
//                }
//            } catch (Exception e) {
//            }
//
//            try {
//                Optional<DeliveryTerms> deliveryTermsOptional = deliveryTermsRepo.findById(purchaseOrder.get().getDelivery_terms());
//                if (deliveryTermsOptional.isPresent()) {
//                    purchaseOrder.get().setDelivery_terms_details(deliveryTermsOptional.get());
//                }
//            } catch (Exception e) {
//            }
//
//            try {
//                Optional<PaymentTypes> paymentTypesOptional = paymentTypesRepo.findById(purchaseOrder.get().getPayment());
//                if (paymentTypesOptional.isPresent()) {
//                    purchaseOrder.get().setPaymentTypes(paymentTypesOptional.get());
//                }
//            } catch (Exception e) {
//            }
//
////            try {
//            for (int i = 0; i < purchaseOrder.get().getPurchaseOrderItemsList().size(); i++) {
//                Integer uom = purchaseOrder.get().getPurchaseOrderItemsList().get(i).getUom();
//              try{
//                  if (uom != null) {
//                      purchaseOrder.get().getPurchaseOrderItemsList().get(i).setUnitOfMeasurement(unitOfMeasurementRepo.findById(uom).get());
//                  }
//              }catch (Exception e){}
//            }
////            } catch (Exception e) {
////            }
//            return ResponseEntity.ok(purchaseOrder.get());
//        } else {
//            logger.info("Invalid order id");
//            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Invalid order id");
//        }
//
//    }


    @Transactional
    public ResponseEntity<?> FindById(int id) {
        Optional<PurchaseOrder> purchaseOrderOptional = purchaseOrderRepo.findById(id);
        if (purchaseOrderOptional.isPresent()) {
            PurchaseOrder purchaseOrder = purchaseOrderOptional.get();

            // Fetch and set payment terms details
            if (purchaseOrder.getPayment_terms() != null) {
                paymentTermsRepo.findById(purchaseOrder.getPayment_terms())
                        .ifPresent(purchaseOrder::setPayment_terms_detail);
            }

            try {
                if (purchaseOrder.getDelivery_terms() != null && purchaseOrder.getDelivery_terms() != 0) {
                    deliveryTermsRepo.findById(purchaseOrder.getDelivery_terms())
                            .ifPresent(purchaseOrder::setDelivery_terms_details);
                }
            } catch (Exception e) {
            }

            if (purchaseOrder.getPayment() != null && purchaseOrder.getPayment() != 0) {
                paymentTypesRepo.findById(purchaseOrder.getPayment())
                        .ifPresent(purchaseOrder::setPaymentTypes);
            }
            if (purchaseOrder.getPayment() != null && purchaseOrder.getPayment() != 0) {
                incotermsRepo.findById(purchaseOrder.getIncoterms())
                        .ifPresent(purchaseOrder::setIncotermsDetails);
            }
            // Fetch and set unit of measurement details for each item
            for (PurchaseOrderItem item : purchaseOrder.getPurchaseOrderItemsList()) {
                if (item.getUom() != null) {
                    unitOfMeasurementRepo.findById(item.getUom())
                            .ifPresent(item::setUnitOfMeasurement);
                }
            }

            return ResponseEntity.ok(purchaseOrder);
        } else {
            throw new EntityNotFoundException("Invalid order id");
        }
    }

    public ResponseEntity<?> FindBySupplier(int id, int page, int page_size) {
        List<PurchaseOrder> purchaseOrder = purchaseOrderRepo.findBySupplier_id(id, PageRequest.of(page, page_size));
        return ResponseEntity.ok(purchaseOrder);

    }

    public ResponseEntity<?> Search(int supplier_id, int location_id, String poid, int page, int pageSize) {
//        List<PurchaseOrder> purchaseOrder = purchaseOrderRepo.findBySupplier_idAndIdIn(supplier_id, poid, PageRequest.of(page, pageSize));
        List<PurchaseOrder> purchaseOrder = purchaseOrderRepo.findBySupplier_idAndPo_noContainsAndLocation_id(supplier_id, poid, location_id, PageRequest.of(page, pageSize));
//        List<PurchaseOrder> purchaseOrder = purchaseOrderRepo.findBySupplier_idAndPo_noAndLocation_id(supplier_id, poid,location_id, PageRequest.of(page, pageSize));
        return ResponseEntity.ok(purchaseOrder);
    }

    public ResponseEntity<?> SearchStatusWise(int supplier_id, int location_id, String poid, int page, int pageSize, int approve) {
        List<PurchaseOrder> purchaseOrder = purchaseOrderRepo.findBySupplier_idAndPo_noContainsAndLocation_id(supplier_id, poid, location_id, approve == 1 ? true : false, PageRequest.of(page, pageSize));
        return ResponseEntity.ok(purchaseOrder);
    }

    public ResponseEntity<?> SearchStatus5(int supplier_id, int location_id, String poid, int page, int pageSize) {
        List<PurchaseOrder> purchaseOrder = purchaseOrderRepo.findBySupplier_idAndPo_noContainsAndLocation_idStatus5(supplier_id, poid, location_id, PageRequest.of(page, pageSize));
        return ResponseEntity.ok(purchaseOrder);
    }

    public ResponseEntity<?> FindAll(int page, int pageSize, int order) {
        return ResponseEntity.ok(purchaseOrderRepo.findAll(PageRequest.of(page, pageSize, Sort.by(order == 0 ? Sort.Direction.ASC : Sort.Direction.DESC, "id"))));
    }

    public ResponseEntity<?> FilterByDate(String from, String to, int page, int page_size) {
        return ResponseEntity.ok(purchaseOrderRepo.findByPo_dateGreaterThanEqualAndPo_dateLessThanEqualOrderByIdDesc(LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd")), PageRequest.of(page, page_size)));
    }


//    public void GeneratePOReport(int id) throws JRException {
////        id=42;
////        JasperReport jasperReport = JasperCompileManager.compileReport("/Users/sandunvidusankha/JaspersoftWorkspace/MyReports/purchase_order.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(configRepo.findByKey("po_report_template_url").getValue());
//
//        List reportData = new ArrayList();
//        HashMap<String, Object> params = new HashMap<String, Object>();
//        Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepo.findById(id);
//        Optional<Supplier> supplier = supplierRepo.findById(purchaseOrder.get().getSupplier_id());
//        CompanyAddress companyAddress = companyAddressRepo.findById(purchaseOrder.get().getDelivery_address_from_id()).get();
//        SupplierAddress supplierAddress = supplierAddressRepo.findById(purchaseOrder.get().getSupplier_id()).get();
//        Location location = locationRepo.findById(purchaseOrder.get().getLocation_id()).get();
//        params.put("po_no", purchaseOrder.get().getPo_no());
//        params.put("company_address", companyAddress.getName() + "\n" + companyAddress.getState() + "\n" + companyAddress.getStreet_address() + "\n" + companyAddress.getZip_code());
//        params.put("supplier_address", supplierAddress.getName() + "\n" + supplierAddress.getState() + "\n" + supplierAddress.getStreet_address() + "\n" + supplierAddress.getZip_code());
//        params.put("subtotal", String.valueOf(purchaseOrder.get().getSub_total()));
//        params.put("shipping", "-");
//        params.put("discount", String.valueOf(purchaseOrder.get().getTotal_discount()));
//        params.put("taxes", String.valueOf(purchaseOrder.get().getTax()));
//        params.put("tot_total", String.valueOf(purchaseOrder.get().getTotal()));
//        params.put("create_at", String.valueOf(purchaseOrder.get().getCreate_at().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
//        params.put("due_date", String.valueOf(purchaseOrder.get().getExpected_delivery_date()));
//        params.put("location_name", String.valueOf(location.getName()));
//        params.put("email", String.valueOf(location.getEmail()));
//        params.put("mobile_number", String.valueOf(location.getMobile_number()));
//        params.put("address", String.valueOf(location.getStreet_address()));
//        PurchaseOrderReportModel purchaseOrderReportModel = new PurchaseOrderReportModel();
//        List<PurchaseOrderReportModel> purchaseOrderReportModel1 = new ArrayList<>();
//        Item item = new Item();
//        List<PurchaseOrderItem> purchaseOrderItemsList = purchaseOrderItemRepo.findByOrder_id(id);
//        for (int i = 0; i < purchaseOrderItemsList.size(); i++) {
//            item = new Item();
//            item = itemRepo.findById(purchaseOrderItemsList.get(i).getItem_id()).get();
//            purchaseOrderReportModel = new PurchaseOrderReportModel();
//            purchaseOrderReportModel.setId(i + 1);
//            purchaseOrderReportModel.setDescription(item.getItem_id());
//            purchaseOrderReportModel.setQty(String.valueOf(purchaseOrderItemsList.get(i).getQuantity()));
//            purchaseOrderReportModel.setUnitprice(String.valueOf(purchaseOrderItemsList.get(i).getCost_price()));
//            purchaseOrderReportModel.setTotal(String.valueOf(purchaseOrderItemsList.get(i).getAmount()));
//            purchaseOrderReportModel1.add(purchaseOrderReportModel);
//        }
//        System.out.println(purchaseOrderReportModel1.size());
//        // Create a JasperPrint
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(purchaseOrderReportModel1));
////        byte[] pdfData = JasperExportManager.exportReportToPdf(jasperPrint);
//        String path = configRepo.findByKey("po_report_generate_url").getValue() + id + ".pdf";
//        JasperExportManager.exportReportToPdfFile(jasperPrint, path);
////        "<h1>This is an HTML email example</h1>"
//
//        EmailConnection emailConnection = new EmailConnection(configRepo);
//        emailConnection.MailSend("PO ", "Test Content #Fire", supplier.get().getEmail_address(), path);
//
//        File file = new File(path);
//        if (file.exists()) {
//            file.delete();
//        }
//        // Export the report to a PDF file
////        ModelAndView modelAndView = new ModelAndView("report");
////        HttpHeaders headers = new HttpHeaders();
////        headers.setContentType(MediaType.APPLICATION_PDF);
////        headers.setContentDispositionFormData("inline", "po.pdf");
//
//// Return the PDF data as a downloadable file
////        return ResponseEntity.ok()
////                .headers(headers)
////                .body(pdfData);
//    }

    public ResponseEntity<?> GetReport(String from_date, String to_date) {
        LocalDateTime fromDate = LocalDateTime.parse(from_date + " 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime toDate = LocalDateTime.parse(to_date + " 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

//        return ResponseEntity.ok(purchaseOrderRepo.findByCreate_atGreaterThanEqualAndCreate_atLessThanEqualOrderByIdDesc(fromDate,toDate));
        List<PurchaseOrder> purchaseOrderList = purchaseOrderRepo.findByCreate_atGreaterThanEqualAndCreate_atLessThanEqualOrderByIdDesc(fromDate, toDate);
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Report");

        int rowNum = 0;
        Row row = sheet.createRow(rowNum++);

        CellStyle boldStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        boldStyle.setFont(font);

        Cell cell = row.createCell(0);
        cell.setCellValue("Company: ");
        cell.setCellStyle(boldStyle);

        cell = row.createCell(1);
        cell.setCellValue(" ZI GLOBAL LLC");
        cell.setCellStyle(boldStyle);

        row = sheet.createRow(rowNum++);

        cell = row.createCell(0);
        cell.setCellValue("Report: ");
        cell.setCellStyle(boldStyle);
        ;
        cell = row.createCell(1);
        cell.setCellValue("Purchase Order Report");
        cell.setCellStyle(boldStyle);

        row = sheet.createRow(rowNum++);
        cell = row.createCell(0);
        cell.setCellValue("Period : ");
        cell.setCellStyle(boldStyle);

        cell = row.createCell(1);
        cell.setCellValue(formatter.format(LocalDate.parse(from_date)) + " To " + formatter.format(LocalDate.parse(to_date)));
        cell.setCellStyle(boldStyle);

        row = sheet.createRow(rowNum++);
        row = sheet.createRow(rowNum++);

        row.createCell(0).setCellValue("Po");
        Cell cellPo = row.createCell(0);
        cellPo.setCellValue("Po");
        cellPo.setCellStyle(boldStyle);

        Cell cellCustomer = row.createCell(1);
        cellCustomer.setCellValue("Customer");
        cellCustomer.setCellStyle(boldStyle);

        Cell cellDate = row.createCell(2);
        cellDate.setCellValue("Date");
        cellDate.setCellStyle(boldStyle);

        Cell cellTotal = row.createCell(3);
        cellTotal.setCellValue("Total");
        cellTotal.setCellStyle(boldStyle);


        row = sheet.createRow(rowNum++);
        for (int i = 0; i < purchaseOrderList.size(); i++) {
            row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(purchaseOrderList.get(i).getPo_no());
            row.createCell(1).setCellValue(" ");
            row.createCell(2).setCellValue(formatter.format(purchaseOrderList.get(i).getCreate_at()));
//            row.createCell(3).setCellValue("");
            row.createCell(3).setCellValue(purchaseOrderList.get(i).getTotal());
//            Header need to add for items - initialHeaders


            if (!purchaseOrderList.get(i).getPurchaseOrderItemsList().isEmpty()) {
                row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue("Item");
                row.createCell(1).setCellValue("Quantity");
                row.createCell(2).setCellValue("Amount");
//                row.createCell(3).setCellValue(purchaseOrderList.get(i).getPurchaseOrderItemsList().get(j).getAmount());
            }
            for (int j = 0; j < purchaseOrderList.get(i).getPurchaseOrderItemsList().size(); j++) {
                row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(" ");
//                row.createCell(1).setCellValue(purchaseOrderList.get(i).getPurchaseOrderItemsList().get(j).getOrder_id());
                row.createCell(1).setCellValue(purchaseOrderList.get(i).getPurchaseOrderItemsList().get(j).getQuantity());
                row.createCell(2).setCellValue(purchaseOrderList.get(i).getPurchaseOrderItemsList().get(j).getAmount());
            }

            row = sheet.createRow(rowNum++);
            row = sheet.createRow(rowNum++);

        }

        try {
            // Auto-resize column widths
            for (int i = 0; i < 6; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            workbook.write(baos);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "generated.xlsx");
            return ResponseEntity.ok().headers(headers).body(baos.toByteArray());


        } catch (Exception e) {
            System.out.println(e);
            return null;

        }

    }

//    public ResponseEntity<?> Approve(int id) {
//        Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepo.findById(id);
//        if (purchaseOrder.isPresent()) {
//            purchaseOrder.get().setApproved(true);
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            Optional<User> userOptional = userRepository.findByUsername(authentication.getName());
//            if (userOptional.isPresent()) {
//                purchaseOrder.get().setApproved_user(userOptional.get().getId());
//                purchaseOrderRepo.save(purchaseOrder.get());
//                return ResponseEntity.ok("success");
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Invalid User authentication !");
//            }
//
//
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid id ");
//        }
//    }

    public ResponseEntity<?> SearchStatus5Approved(int supplier_id, int location_id, String poid, int page, int pageSize) {
        List<PurchaseOrder> purchaseOrder = purchaseOrderRepo.findBySupplier_idAndPo_noContainsAndLocation_idStatus5Active(supplier_id, poid, location_id, PageRequest.of(page, pageSize));
        return ResponseEntity.ok(purchaseOrder);
    }

    public ResponseEntity<?> StatusWisePo(int page, int page_size, int approve) {
        return ResponseEntity.ok(purchaseOrderRepo.findByApprovedOrderByIdDesc(approve == 1 ? true : false, PageRequest.of(page, page_size, Sort.by(Sort.Direction.DESC, "id"))));
    }

    public ResponseEntity<?> getReportPdf(HttpServletResponse response, String from_date, String to_date) throws IOException {
        Document document = new Document(PageSize.A3.rotate());
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Paragraph title = new Paragraph("Purchase Order Report", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16));
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        Paragraph company = new Paragraph("ZI GLOBAL LLC", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
        company.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(company);

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");

        String formattedFrom = "";
        String formattedTo = "";

        try {
            Date fromDate = inputFormat.parse(from_date);
            Date toDate = inputFormat.parse(to_date);

            formattedFrom = outputFormat.format(fromDate);
            formattedTo = outputFormat.format(toDate);
        } catch (Exception e) {
            logger.error("Error converting date");
        }

        Paragraph dateRange = new Paragraph("Date from " + formattedFrom + " to " + formattedTo, FontFactory.getFont(FontFactory.HELVETICA, 12));
        dateRange.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(dateRange);

        document.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        String[] headers = {"Id", "Customer", "Date", "Item", "Quantity", "Amount"};
        for (String header : headers) {
            PdfPCell headerCell = new PdfPCell(new Phrase(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            headerCell.setBorderWidth(0f);
            headerCell.setPadding(10);
            table.addCell(headerCell);
        }

        DecimalFormat decimalFormat = new DecimalFormat("#.000");

        LocalDateTime fromDate = LocalDateTime.parse(from_date + " 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime toDate = LocalDateTime.parse(to_date + " 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<PurchaseOrder> purchaseOrderList = purchaseOrderRepo.findByCreate_atGreaterThanEqualAndCreate_atLessThanEqualOrderByIdDesc(fromDate, toDate);

        for (int i = 0; i < purchaseOrderList.size(); i++) {
            PurchaseOrder order = purchaseOrderList.get(i);
            boolean isFirstRow = true;

            for (int j = 0; j < order.getPurchaseOrderItemsList().size(); j++) {
                PurchaseOrderItem item = order.getPurchaseOrderItemsList().get(j);

                if (isFirstRow) {
                    addCell(table, order.getPo_no());
                    addCell(table, order.getSuppliers() != null ? " " : "-");
                    addCell(table, order.getCreate_at() != null ? formatter.format(order.getCreate_at()) : "-");
                    isFirstRow = false;
                } else {
                    addCell(table, "");
                    addCell(table, "");
                    addCell(table, "");
                }

                addCell(table, " ");
                addCell(table, decimalFormat.format(item.getQuantity()));
                addCell(table, decimalFormat.format(item.getAmount()));

                if (j == order.getPurchaseOrderItemsList().size() - 1) {

                    PdfPCell headerCell = new PdfPCell(new Phrase("Total", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
                    headerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    headerCell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                    headerCell.setPadding(10);
                    table.addCell(headerCell);

                    addCell(table, "");
                    addCell(table, "");
                    addCell(table, "");
                    addCell(table, "");

                    PdfPCell totalCell = new PdfPCell(new Phrase(decimalFormat.format(order.getTotal()), FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
                    totalCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    totalCell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
                    totalCell.setPadding(10);
                    table.addCell(totalCell);
                }
            }

            for(int r=0; r<6; r++){
                PdfPCell extraBorderCell = new PdfPCell(new Phrase(""));
                extraBorderCell.setBorder(Rectangle.BOTTOM);
                extraBorderCell.setFixedHeight(2f);
                table.addCell(extraBorderCell);
            }

            for (int r=0; r<12; r++){
                addCellWithoutBorder(table, " ");
            }
        }

        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Stock_Replenishment_Report_" + new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss").format(new Date()) + ".pdf";
        response.setHeader(headerKey, headerValue);

        document.add(table);
        document.close();

        return ResponseEntity.ok().build();
    }


    private void addCell(PdfPTable table, String content) {
        PdfPCell cell = new PdfPCell(new Phrase(content != null ? content : "-"));
        cell.setPadding(10);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
        table.addCell(cell);
    }

    private void addCellWithoutBorder(PdfPTable table, String content) {
        PdfPCell cell = new PdfPCell(new Phrase(content != null ? content : "-"));
        cell.setPadding(10);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorderWidth(0);
        table.addCell(cell);
    }



}
