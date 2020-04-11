package com.vacation.manager.service;

import com.vacation.manager.repository.EnterpriseRepository;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseService {

    private EnterpriseRepository enterpriseRepository;

    public EnterpriseService(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = enterpriseRepository;
    }

    public EnterpriseRepository getEnterpriseRepository() {
        return enterpriseRepository;
    }
}
