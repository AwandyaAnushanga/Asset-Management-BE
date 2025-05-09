package com.nexeyo.erp.PurchaseOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(path = "/api/v1/purchase/")
public class PurchaseOrderController {
    @Autowired
    PurchaseOrderService purchaseOrderService;

    @PostMapping(path = "order")
    ResponseEntity<?> Add(@RequestBody PurchaseOrder purchaseOrder) {
        return purchaseOrderService.Add(purchaseOrder);
    }

    @GetMapping(path = "order-cancel/{order_id}")
    ResponseEntity<?> Cancel(@PathVariable int order_id) {
        return purchaseOrderService.CancelOrder(order_id);
    }

    @GetMapping(path = "order-id/{id}")
    ResponseEntity<?> FindById(@PathVariable int id) {
        return purchaseOrderService.FindById(id);
    }

    @GetMapping(path = "order-suppler-id/{id}/{page}/{page_size}")
    ResponseEntity<?> PoFindBySupplierId(@PathVariable int id, @PathVariable int page, @PathVariable int page_size) {
        return purchaseOrderService.FindBySupplier(id, page, page_size);
    }

    @PostMapping(path = "order-search-id/{id}/{location_id}/{page}/{page_size}")
    ResponseEntity<?> PoSearchBySupplierIdAndPoNumber(@PathVariable int id, @PathVariable int location_id, @PathVariable int page, @PathVariable int page_size, @RequestParam String poid) {
        return purchaseOrderService.Search(id, location_id, poid, page, page_size);
    }

    @PostMapping(path = "order-search-id/{id}/{location_id}/{page}/{page_size}/{approve}")
    ResponseEntity<?> PoSearchBySupplierIdAndPoNumberStatusWise(@PathVariable int id, @PathVariable int location_id, @PathVariable int page, @PathVariable int page_size, @RequestParam String poid,@PathVariable int approve) {
        return purchaseOrderService.SearchStatusWise(id, location_id, poid, page, page_size,approve);
    }

    @PostMapping(path = "order-search-id-active/{id}/{location_id}/{page}/{page_size}")
    ResponseEntity<?> PoSearchBySupplierIdAndPoNumberStatus5(@PathVariable int id, @PathVariable int location_id, @PathVariable int page, @PathVariable int page_size, @RequestParam String poid) {
        return purchaseOrderService.SearchStatus5(id, location_id, poid, page, page_size);
    }

    @PostMapping(path = "order-search-id-approved/{id}/{location_id}/{page}/{page_size}")
    ResponseEntity<?> PoSearchBySupplierIdAndPoNumberStatus5AndApproved(@PathVariable int id, @PathVariable int location_id, @PathVariable int page, @PathVariable int page_size, @RequestParam String poid) {
        return purchaseOrderService.SearchStatus5Approved(id, location_id, poid, page, page_size);
    }


    @GetMapping(path = "order/{page}/{page_size}/{order}")
    ResponseEntity<?> PoFindAll(@PathVariable int page, @PathVariable int page_size, @PathVariable int order) {
        return purchaseOrderService.FindAll(page, page_size, order);
    }

    @PostMapping(path = "order-date-filter/{page}/{page_size}")
    ResponseEntity<?> FilterByPoDate(@PathVariable int page, @PathVariable int page_size, @RequestParam String from, @RequestParam String to) {
        return purchaseOrderService.FilterByDate(from, to, page, page_size);
    }

//    @GetMapping(path = "report")
//    void aa() throws JRException {
//        purchaseOrderService.GeneratePOReport(42);
//    }

    @PostMapping(path = "report")
    ResponseEntity<?> GetReport(@RequestParam String from_date, @RequestParam String to_date) {
        return purchaseOrderService.GetReport(from_date, to_date);
    }

//    @GetMapping(path = "approve/{id}")
//    ResponseEntity<?> Approve(@PathVariable int id) {
//        return purchaseOrderService.Approve(id);
//    }

    @GetMapping(path = "approve/{page}/{page_size}/{approve}")
    ResponseEntity<?> GetNonApprovedPo(@PathVariable int page,@PathVariable int page_size,@PathVariable int approve) {
        return purchaseOrderService.StatusWisePo(page,page_size,approve);
    }

    @GetMapping(path = "report-pdf")
    ResponseEntity<?> getReportPdf(HttpServletResponse response, @RequestParam String from_date, @RequestParam String to_date) throws IOException {
        return purchaseOrderService.getReportPdf(response, from_date, to_date);
    }


}
