package com.vacation.manager.service;

import com.vacation.manager.exception.AppExceptionBuilder;
import com.vacation.manager.messages.EnterprisesMessages;
import com.vacation.manager.messages.PaidLeavesMessages;
import com.vacation.manager.model.PaidLeave;
import com.vacation.manager.model.api.WorkerLeaveApi;
import com.vacation.manager.model.api.WorkerLeaveListApi;
import com.vacation.manager.repository.LeaveRepository;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;
    private final WorkersService workersService;

    public LeaveService(LeaveRepository leaveRepository, WorkersService workersService) {
        this.leaveRepository = leaveRepository;
        this.workersService = workersService;
    }

    public PaidLeave createWorkerLeaves(PaidLeave paidLeave, String mail, String enterprise) {
        getWorkerLeaves(mail, enterprise).forEach(tmpPaidLeave -> {
            if (paidLeave.getStartDate().isBefore(tmpPaidLeave.getStartDate()) &&
                    paidLeave.getEndDate().isAfter(tmpPaidLeave.getEndDate()))
                throw new AppExceptionBuilder().addError(PaidLeavesMessages.FAILURE_EXCEPTION_DURATION).build();
        });
        long id = workersService.getWorkerByEmailAndEnterprise(mail, enterprise).getId();
        return leaveRepository.createPaidLeave(paidLeave, id)
                .orElseThrow(() -> new AppExceptionBuilder().addError(PaidLeavesMessages.CREATE_FAILURE).build());
    }

    public List<PaidLeave> getWorkerLeaves(String mail, String enterprise) {
        long id = workersService.getWorkerByEmailAndEnterprise(mail, enterprise).getId();
        return leaveRepository.getPaidLeavesByWorkerId(id);
    }

    public List<WorkerLeaveListApi> getActiveLeavesInEnterprise(Long enterpriseId) {
        return leaveRepository.getActivePaidLeavesByWorkerEnterpriseId(enterpriseId);
    }

    public List<PaidLeave> getHistoryLeavesInEnterprise(Long enterpriseId, int page) {
        List<PaidLeave> reversed = new ArrayList<>();
        new LinkedList<>(leaveRepository.getHistoryLeavesByWorkerEnterpriseId(enterpriseId, page))
                .descendingIterator()
                .forEachRemaining(reversed::add);
        return  reversed;

    }

    public WorkerLeaveApi getWorkerLeaveDeatils(Long leaveId) {
        return leaveRepository.getDetailsById(leaveId)
                .orElseThrow(() -> new AppExceptionBuilder().addError(PaidLeavesMessages.NOT_FOUND).build());

    }

    public PaidLeave putLeaveStatus(Long id, String status) {
        return leaveRepository.setStatus(id, status)
                .orElseThrow(() -> new AppExceptionBuilder().addError(PaidLeavesMessages.UPDATE_FAILURE).build());

    }
}
