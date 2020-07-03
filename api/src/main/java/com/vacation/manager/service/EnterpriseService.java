package com.vacation.manager.service;


import com.vacation.manager.exception.AppExceptionBuilder;
import com.vacation.manager.exception.messages.EnterprisesMessages;
import com.vacation.manager.model.Enterprise;
import com.vacation.manager.repositoryP.EnterpriseRepository;
import org.springframework.stereotype.Service;


@Service
public class EnterpriseService {

    private EnterpriseRepository enterpriseRepository;

    public EnterpriseService(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = enterpriseRepository;
    }

    public Enterprise createEnterprise(Enterprise enterprise) {
        return enterpriseRepository.createEnterprise(enterprise)
                .orElseThrow(() -> new AppExceptionBuilder().addError(EnterprisesMessages.CREATE_FAILURE).build());
    }



}
