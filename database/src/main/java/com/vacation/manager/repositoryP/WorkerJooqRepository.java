package com.vacation.manager.repositoryP;

import com.vacation.manager.model.Role;
import com.vacation.manager.model.RoleWorker;
import com.vacation.manager.model.Worker;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;

import static com.vacation.manager.jooq.tables.Role.ROLE;
import static com.vacation.manager.jooq.tables.RoleWorker.ROLE_WORKER;
import static com.vacation.manager.jooq.tables.Worker.WORKER;

public class WorkerJooqRepository {

    private final DSLContext dsl;

    public WorkerJooqRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Optional<Worker> findByEmail(String email){
        return dsl.select().
                from(WORKER)
                .where(WORKER.EMAIL.eq(email))
                .fetchOptionalInto(Worker.class);
    }

    public List<Role> getUserRoles(String username){
        return dsl.select()
                .from(WORKER, ROLE_WORKER, ROLE)
                .where(WORKER.ID.eq((int) (long) findByEmail(username).get().getId()))
                .and(ROLE_WORKER.WORKER_ID.eq(WORKER.ID))
                .and(ROLE_WORKER.ROLE_ID.eq(ROLE.ID))
                .fetchInto(Role.class);

    }


    public Optional<Worker> createWorker(Worker worker) {
        var result = dsl.insertInto(WORKER)
                .set(WORKER.NAME, worker.getName())
                .set(WORKER.OCCUPATION, worker.getOccupation())
                .set(WORKER.EMAIL, worker.getEmail())
                .set(WORKER.PASSWORD, worker.getPassword())
                .set(WORKER.ENTERPRISE_ID, worker.getEnterpriseId().intValue())
                .returning()
                .fetchOne();
        return Optional.ofNullable(result.into(Worker.class));
    }

    public Optional<RoleWorker> createRoleToWorker(Integer workerId, Integer roleId) {
        var result = dsl.insertInto(ROLE_WORKER)
                .set(ROLE_WORKER.WORKER_ID, workerId)
                .set(ROLE_WORKER.ROLE_ID, roleId)
                .returning()
                .fetchOne();
        return Optional.ofNullable(result.into(RoleWorker.class));
    }
}
