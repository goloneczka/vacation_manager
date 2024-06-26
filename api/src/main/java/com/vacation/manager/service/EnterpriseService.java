package com.vacation.manager.service;


import com.vacation.manager.exception.AppExceptionBuilder;
import com.vacation.manager.messages.EnterprisesMessages;
import com.vacation.manager.model.Enterprise;
import com.vacation.manager.repository.EnterpriseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


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


    public Enterprise confirmEnterprise(String enterpriseName) {
        if(enterpriseRepository.getEnterpriseByName(enterpriseName)
            .orElseThrow(() -> new AppExceptionBuilder().addError(EnterprisesMessages.NOT_FOUND).build()).getConfirmed())
            throw new AppExceptionBuilder().addError(EnterprisesMessages.ALREADY_CONFIRMED).build();
        return enterpriseRepository.confirmEnterprise(enterpriseName)
                .orElseThrow(() -> new AppExceptionBuilder().addError(EnterprisesMessages.CREATE_CONFIRM_FAILURE).build());

    }


    public Enterprise getEnterpriseByName(String name) {
        return enterpriseRepository.getEnterpriseByName(name)
                .orElseThrow(() -> new AppExceptionBuilder().addError(EnterprisesMessages.NOT_FOUND).build());

    }

    public Enterprise updateRestart(String enterpriseName, LocalDate now) {
        return enterpriseRepository.setUpdatedTime(enterpriseName, now)
                .orElseThrow(() -> new AppExceptionBuilder().addError(EnterprisesMessages.UPDATE_FAILURE).build());

    }
}
