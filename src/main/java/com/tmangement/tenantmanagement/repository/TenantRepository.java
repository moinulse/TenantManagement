package com.tmangement.tenantmanagement.repository;

import com.tmangement.tenantmanagement.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {
}
