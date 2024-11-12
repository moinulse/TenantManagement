package com.tmangement.tenantmanagement.controller;

import com.tmangement.tenantmanagement.model.Bill;
import com.tmangement.tenantmanagement.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping
    public List<Bill> getAllBills() {
        return billService.getAllBills();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long id) {
        Bill bill = billService.getBillById(id);
        return bill != null ? ResponseEntity.ok(bill) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Bill createBill(@RequestBody Bill bill) {
        return billService.saveBill(bill);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bill> updateBill(@PathVariable Long id, @RequestBody Bill updatedBill) {
        Bill bill = billService.getBillById(id);
        if (bill != null) {
            bill.setAmount(updatedBill.getAmount());
            bill.setDescription(updatedBill.getDescription());
            bill.setDueDate(updatedBill.getDueDate());
            bill.setStatus(updatedBill.getStatus());
            return ResponseEntity.ok(billService.saveBill(bill));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long id) {
        if (billService.getBillById(id) != null) {
            billService.deleteBill(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}