package com.vacation.manager.repository;

import com.vacation.manager.model.Role;
import com.vacation.manager.model.RoleWorker;
import com.vacation.manager.model.Worker;
import com.vacation.manager.model.WorkerExtraDays;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;

import static com.vacation.manager.jooq.tables.Enterprise.ENTERPRISE;
import static com.vacation.manager.jooq.tables.Role.ROLE;
import static com.vacation.manager.jooq.tables.RoleWorker.ROLE_WORKER;
import static com.vacation.manager.jooq.tables.Worker.WORKER;
import static com.vacation.manager.jooq.tables.WorkerExtraDays.WORKER_EXTRA_DAYS;


public class WorkerRepository {

    private final DSLContext dsl;

    public WorkerRepository(DSLContext dsl) {
        this.dsl = dsl;
    }


    public Optional<Worker> findConfirmedByEmailAndEnterprise(String email, String enterprise) {
        return dsl.select()
                .from(WORKER)
                .where(WORKER.EMAIL.eq(email))
                .and(WORKER.CONFIRMED.eq(true))
                .and(WORKER.ENTERPRISE_ID.eq(
                        dsl.select(ENTERPRISE.ID)
                                .from(ENTERPRISE)
                                .where(ENTERPRISE.ENTERPRISE_NAME.eq(enterprise))))
                .fetchOptionalInto(Worker.class);
    }

    public List<Role> getUserRoles(Long id) {
        return dsl.select()
                .from(WORKER, ROLE_WORKER, ROLE)
                .where(WORKER.ID.eq((int) (long) id))
                .and(ROLE_WORKER.WORKER_ID.eq(WORKER.ID))
                .and(ROLE_WORKER.ROLE_ID.eq(ROLE.ID))
                .fetchInto(Role.class);
    }


    public Optional<Worker> createWorker(Worker worker) {
        try {
            var result = dsl.insertInto(WORKER)
                    .set(WORKER.NAME, worker.getName())
                    .set(WORKER.OCCUPATION, worker.getOccupation())
                    .set(WORKER.EMAIL, worker.getEmail())
                    .set(WORKER.PASSWORD, worker.getPassword())
                    .set(WORKER.ENTERPRISE_ID, worker.getEnterpriseId().intValue())
                    .set(WORKER.EMPLOYEE_VARS_ID, worker.getEmployeeVarsId().intValue())
                    .set(WORKER.HIRED, worker.getHired())
                    .returning()
                    .fetchOne();
            return Optional.ofNullable(result.into(Worker.class));
        } catch (RuntimeException ex) {
            return Optional.empty();
        }
    }

    public Optional<RoleWorker> createRoleToWorker(Integer workerId, Integer roleId) {
        var result = dsl.insertInto(ROLE_WORKER)
                .set(ROLE_WORKER.WORKER_ID, workerId)
                .set(ROLE_WORKER.ROLE_ID, roleId)
                .returning()
                .fetchOne();
        return Optional.ofNullable(result.into(RoleWorker.class));
    }

    public Optional<Worker> confirmWorker(String mail, Long enterpriseId) {
        return dsl
                .update(WORKER)
                .set(WORKER.CONFIRMED, true)
                .where(WORKER.ENTERPRISE_ID.eq((int) (long) enterpriseId))
                .and(WORKER.EMAIL.eq(mail))
                .returning()
                .fetchOptional()
                .map(record -> record.into(Worker.class));
    }

    public List<Worker> getEmployeesByEnterpriseId(Long enterpriseId) {
        return dsl.selectFrom(WORKER)
                .where(WORKER.ENTERPRISE_ID.eq((int) (long) enterpriseId))
                .and(WORKER.CONFIRMED.eq(true))
                .fetchInto(Worker.class);
    }

    public Optional<WorkerExtraDays> createWorkerVars(int years) {
        try {
            var result = dsl.insertInto(WORKER_EXTRA_DAYS)
                    .set(WORKER_EXTRA_DAYS.SENIORITY, years)
                    .returning()
                    .fetchOne();
            return Optional.ofNullable(result.into(WorkerExtraDays.class));
        } catch (RuntimeException ex) {
            return Optional.empty();
        }
    }

    public Optional<WorkerExtraDays> getWorkerExtraDaysById(Long varsId) {
        return dsl.select()
                .from(WORKER_EXTRA_DAYS)
                .where(WORKER_EXTRA_DAYS.ID.eq((int) (long) varsId))
                .fetchOptionalInto(WorkerExtraDays.class);

    }

    public Optional<Worker> setWorker(Long id, Worker worker) {
        return dsl
                .update(WORKER)
                .set(WORKER.OCCUPATION, worker.getOccupation())
                .set(WORKER.EMAIL, worker.getEmail())
                .set(WORKER.HIRED, worker.getHired())
                .where(WORKER.ID.eq((int) (long) id))
                .returning()
                .fetchOptional()
                .map(record -> record.into(Worker.class));
    }

    public Optional<WorkerExtraDays> setWorkerExtraDaysById(Long varsId, WorkerExtraDays tmpWorkerExtraDays) {
        return dsl
                .update(WORKER_EXTRA_DAYS)
                .set(WORKER_EXTRA_DAYS.ANNUAL_EXTRA_DAYS, tmpWorkerExtraDays.getAnnualExtraDays())
                .set(WORKER_EXTRA_DAYS.EXTRA_DAYS, tmpWorkerExtraDays.getExtraDays())
                .set(WORKER_EXTRA_DAYS.SENIORITY, tmpWorkerExtraDays.getSeniority())
                .where(WORKER_EXTRA_DAYS.ID.eq((int) (long) varsId))
                .returning()
                .fetchOptional()
                .map(record -> record.into(WorkerExtraDays.class));
    }
}
