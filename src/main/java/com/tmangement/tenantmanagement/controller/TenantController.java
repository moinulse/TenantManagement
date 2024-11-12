package com.tmangement.tenantmanagement.controller;

import com.tmangement.tenantmanagement.model.Tenant;
import com.tmangement.tenantmanagement.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @GetMapping
    public List<Tenant> getAllTenants() {
        return tenantService.getAllTenants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tenant> getTenantById(@PathVariable Long id) {
        return tenantService.getTenantById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tenant createTenant(@RequestBody Tenant tenant) {
        return tenantService.saveTenant(tenant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tenant> updateTenant(@PathVariable Long id, @RequestBody Tenant tenantDetails) {
        return tenantService.getTenantById(id).map(existingTenant -> {
            existingTenant.setName(tenantDetails.getName());
            existingTenant.setEmail(tenantDetails.getEmail());
            existingTenant.setPhone(tenantDetails.getPhone());
            existingTenant.setAddress(tenantDetails.getAddress());
            tenantService.saveTenant(existingTenant);
            return ResponseEntity.ok(existingTenant);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTenant(@PathVariable Long id) {
        if (tenantService.getTenantById(id).isPresent()) {
            tenantService.deleteTenant(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
