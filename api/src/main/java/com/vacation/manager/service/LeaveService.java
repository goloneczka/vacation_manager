package com.vacation.manager.service;

import com.vacation.manager.exception.AppExceptionBuilder;
import com.vacation.manager.messages.PaidLeavesMessages;
import com.vacation.manager.model.PaidLeave;
import com.vacation.manager.repository.LeaveRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;
    private final WorkersService workersService;

    public LeaveService(LeaveRepository leaveRepository, WorkersService workersService) {
        this.leaveRepository = leaveRepository;
        this.workersService = workersService;
    }

    public PaidLeave createWorkerLeaves(PaidLeave paidLeave, String mail, String enterprise) {
        long id = workersService.getWorkerByEmailAndEnterprise(mail, enterprise).getId();
        return leaveRepository.createPaidLeave(paidLeave, id)
                .orElseThrow(() -> new AppExceptionBuilder().addError(PaidLeavesMessages.CREATE_FAILURE).build());
    }

    public List<PaidLeave> getWorkerLeaves(String mail, String enterprise) {
        long id = workersService.getWorkerByEmailAndEnterprise(mail, enterprise).getId();
        return leaveRepository.getPaidLeavesByWorkerId(id);
    }
}
