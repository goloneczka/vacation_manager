package com.vacation.manager.service;

import com.vacation.manager.exception.AppException;
import com.vacation.manager.exception.AppExceptionBuilder;
import com.vacation.manager.messages.PaidLeavesMessages;
import com.vacation.manager.model.PaidLeave;
import com.vacation.manager.model.Worker;
import com.vacation.manager.model.api.WorkerLeaveListApi;
import com.vacation.manager.model.api.WorkerLeaveApi;
import com.vacation.manager.repository.LeaveRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;


@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;
    private final WorkersService workersService;
    private final EnterpriseService enterpriseService;


    public LeaveService(LeaveRepository leaveRepository, WorkersService workersService, EnterpriseService enterpriseService) {
        this.leaveRepository = leaveRepository;
        this.workersService = workersService;
        this.enterpriseService = enterpriseService;
    }

    @Transactional(rollbackFor = AppException.class)
    public PaidLeave createWorkerLeaves(PaidLeave paidLeave, String mail, String enterprise) {
        Worker tmpWorker = workersService.getWorkerByEmailAndEnterprise(mail, enterprise);
        LocalDate newYear = LocalDate.of(LocalDate.now().plusYears(1).getYear(), 1, 1);
        paidLeave.setDays(countDaysInPaidLeave(paidLeave.getStartDate(), paidLeave.getEndDate()));

        int usedDays = 0;
        int usedDaysPresentYear = 0;
        for (PaidLeave tmpPaidLeave : getWorkerLeaves(mail, enterprise)) {
            if (paidLeave.getStartDate().isBefore(tmpPaidLeave.getStartDate()) &&
                    paidLeave.getEndDate().isAfter(tmpPaidLeave.getEndDate()))
                throw new AppExceptionBuilder().addError(PaidLeavesMessages.CREATE_FAILURE_DURATION).build();
            if(!tmpPaidLeave.getStatus().equals("REJECTED")) {
                if (tmpPaidLeave.getEndDate().isBefore(newYear))
                    usedDaysPresentYear += tmpPaidLeave.getDays();
                usedDays += tmpPaidLeave.getDays();
            }
        }

        if(paidLeave.getStartDate().getYear() == newYear.getYear()){
            if (usedDays - usedDaysPresentYear + paidLeave.getDays() >
                    workersService.getWorkerFutureFreeDays(workersService.getWorkerDateVars(tmpWorker.getEmployeeVarsId()), enterprise))
                throw new AppExceptionBuilder().addError(PaidLeavesMessages.CREATE_FAILURE_AMOUNT).build();
            return leaveRepository.createPaidLeave(paidLeave, tmpWorker.getId())
                    .orElseThrow(() -> new AppExceptionBuilder().addError(PaidLeavesMessages.CREATE_FAILURE).build());
        }
        else if (paidLeave.getStartDate().getYear() != paidLeave.getEndDate().getYear()) {
            PaidLeave newYearPaidLeave;
            try {
                newYearPaidLeave = (PaidLeave) paidLeave.clone();
            } catch (CloneNotSupportedException e) {
                throw new AppExceptionBuilder().addError(e.getMessage()).build(); }

            newYearPaidLeave.setStartDate(newYear);
            newYearPaidLeave.setDays(countDaysInPaidLeave(newYearPaidLeave.getStartDate(), newYearPaidLeave.getEndDate()));
            if (usedDays - usedDaysPresentYear + newYearPaidLeave.getDays() >
                    workersService.getWorkerFutureFreeDays(workersService.getWorkerDateVars(tmpWorker.getEmployeeVarsId()), enterprise))
                throw new AppExceptionBuilder().addError(PaidLeavesMessages.CREATE_FAILURE_AMOUNT).build();
            leaveRepository.createPaidLeave(newYearPaidLeave, tmpWorker.getId())
                    .orElseThrow(() -> new AppExceptionBuilder().addError(PaidLeavesMessages.CREATE_FAILURE).build());

            paidLeave.setEndDate(LocalDate.of(LocalDate.now().getYear(), 12, 31));
            paidLeave.setDays(countDaysInPaidLeave(paidLeave.getStartDate(), paidLeave.getEndDate()));
        }
        if (usedDaysPresentYear + paidLeave.getDays() >
                workersService.getWorkerFreeDays(workersService.getWorkerDateVars(tmpWorker.getEmployeeVarsId()), enterprise))
            throw new AppExceptionBuilder().addError(PaidLeavesMessages.CREATE_FAILURE_AMOUNT).build();
        return leaveRepository.createPaidLeave(paidLeave, tmpWorker.getId())
                .orElseThrow(() -> new AppExceptionBuilder().addError(PaidLeavesMessages.CREATE_FAILURE).build());
    }

    private Long countDaysInPaidLeave(LocalDate start, LocalDate end){
        Set<DayOfWeek> weekend = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        return start.datesUntil(end)
                .filter(d -> !weekend.contains(d.getDayOfWeek()))
                .count() + 1;
    }


    public List<PaidLeave> getWorkerLeaves(String mail, String enterprise) {
        long id = workersService.getWorkerByEmailAndEnterprise(mail, enterprise).getId();
        return leaveRepository.getPaidLeavesByWorkerId(id);
    }

    public List<WorkerLeaveListApi> getUnresolvedByCompanyName(String enterpriseName) {
        return leaveRepository.getUnresolvedByCompanyName(enterpriseName);
    }

    public List<WorkerLeaveListApi> getResolvedByCompanyName(String enterpriseName, int page) {
        List<WorkerLeaveListApi> reversed = new ArrayList<>();
        new LinkedList<>(leaveRepository.getResolvedByCompanyName(enterpriseName, page))
                .descendingIterator()
                .forEachRemaining(reversed::add);
        return reversed;

    }

    public WorkerLeaveApi getWorkerLeaveDeatils(Long leaveId) {
        return leaveRepository.getDetailsById(leaveId)
                .orElseThrow(() -> new AppExceptionBuilder().addError(PaidLeavesMessages.NOT_FOUND).build());

    }

    public PaidLeave putLeaveStatus(Long id, String status) {
        return leaveRepository.setStatus(id, status)
                .orElseThrow(() -> new AppExceptionBuilder().addError(PaidLeavesMessages.UPDATE_FAILURE).build());
    }


    @Transactional(rollbackFor = AppException.class)
    public void deleteOutDated(String enterpriseName, LocalDate now) {
        if (leaveRepository.deleteOutdatedLeavesInCompany(enterpriseName, now) == -1)
            throw new AppExceptionBuilder().addError(PaidLeavesMessages.UPDATE_FAILURE).build();
        enterpriseService.updateRestart(enterpriseName, now);
    }
}
