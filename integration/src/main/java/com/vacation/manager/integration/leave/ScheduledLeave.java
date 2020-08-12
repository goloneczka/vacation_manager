package com.vacation.manager.integration.leave;

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
        leaveRepository.deleteOutdatedLeavesByScheduler(LocalDate.now());
        workerRepository.setWorkerExtraDaysByScheduler();
        enterpriseRepository.updateRestartByScheduler(LocalDate.now());
    }
}
