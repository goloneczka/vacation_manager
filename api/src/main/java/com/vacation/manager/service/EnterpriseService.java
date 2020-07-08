package com.vacation.manager.service;


import com.vacation.manager.exception.AppExceptionBuilder;
import com.vacation.manager.exception.messages.EnterprisesMessages;
import com.vacation.manager.model.Enterprise;
import com.vacation.manager.repository.EnterpriseRepository;
import org.springframework.stereotype.Service;


@Service
public class EnterpriseService {

    private final EnterpriseRepository enterpriseRepository;

    public EnterpriseService(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = enterpriseRepository;
    }

    public Enterprise createEnterprise(Enterprise enterprise) {
        return enterpriseRepository.createEnterprise(enterprise)
                .orElseThrow(() -> new AppExceptionBuilder().addError(EnterprisesMessages.CREATE_FAILURE_DUPLICATED_KEY).build());
    }



}
