package com.vacation.manager.integration.leave;

import com.vacation.manager.model.WorkerExtraDays;
import com.vacation.manager.repository.EnterpriseRepository;
import com.vacation.manager.repository.LeaveRepository;
import com.vacation.manager.repository.WorkerRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@EnableScheduling
@Component
public class ScheduledLeave {

    private final LeaveRepository leaveRepository;
    private final WorkerRepository workerRepository;
    private final EnterpriseRepository enterpriseRepository;



    public ScheduledLeave(LeaveRepository leaveRepository, WorkerRepository workerRepository,
                          EnterpriseRepository enterpriseRepository) {
        this.leaveRepository = leaveRepository;
        this.workerRepository = workerRepository;
        this.enterpriseRepository = enterpriseRepository;
    }

    @Scheduled(cron = "0 0 0 1 1 *")
    public void deleteOutdatedForecasts() {
        LocalDate now = LocalDate.now();
        setWorkersTransitiveDays(now);
        enterpriseRepository.updateRestartByScheduler(now);
        leaveRepository.deleteOutdatedLeavesByScheduler(now);
        workerRepository.setWorkerExtraDaysByScheduler();
    }

    private void setWorkersTransitiveDays(LocalDate now){
        enterpriseRepository.getAll().forEach(enterprise -> {
            workerRepository.getEmployeesByEnterpriseId(enterprise.getId()).forEach(workerInEnterprise -> {
                WorkerExtraDays tmpWorkerExtraDays = workerRepository.getWorkerExtraDaysById(workerInEnterprise.getEmployeeVarsId()).get();
                int diff = getWorkerFreeDays(tmpWorkerExtraDays, enterprise.getEnterpriseName()) - getAmountUsedDays(now, workerInEnterprise.getId());
                tmpWorkerExtraDays.setTransitiveDays(Math.max(diff, 0));
                workerRepository.setWorkerExtraDaysById(tmpWorkerExtraDays.getId(), tmpWorkerExtraDays);
            });
        });
    }

    private int getWorkerFreeDays(WorkerExtraDays tmpWED, String enterprise) {
        return enterpriseRepository.getEnterpriseByName(enterprise).get().getFreeDays()
                + tmpWED.getExtraDays() + (tmpWED.getSeniority() >= 10 ? 6 : 0) + tmpWED.getTransitiveDays();
    }

    private int getAmountUsedDays(LocalDate now, Long workerId){
        return leaveRepository.getPaidLeavesByWorkerId(workerId).stream()
                .filter(tmp -> tmp.getStatus().equals("ACCEPTED") && tmp.getEndDate().isBefore(now))
                .mapToInt(tmp -> (int) tmp.getDays()).sum();

    }
}
