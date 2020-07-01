package com.vacation.manager.repositoryP;

import com.vacation.manager.model.Role;
import com.vacation.manager.model.Worker;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;

import static com.vacation.manager.jooq.workers.tables.Role.ROLE;
import static com.vacation.manager.jooq.workers.tables.RoleWorker.ROLE_WORKER;
import static com.vacation.manager.jooq.workers.tables.Worker.WORKER;

public class WorkerJooqRepository {

    private final DSLContext dsl;

    public WorkerJooqRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Optional<Worker> findByUsername(String username){
        return dsl.select().
                from(WORKER)
                .where(WORKER.USERNAME.eq(username))
                .fetchOptionalInto(Worker.class);
    }

    public List<Role> getUserRoles(String username){
//        return dsl.select().
//                from(WORKER)
//                .join(ROLE_WORKER)
//                .on(ROLE_WORKER.WORKER_ID.eq((int) (long) findByUsername(username).get().getId()))
//                .rightJoin(ROLE)
//                .on(ROLE_WORKER.ROLE_ID.eq(ROLE.ID))
//                .where(WORKER.USERNAME.eq(username))
//                .fetchInto(Role.class);

        return dsl.select()
                .from(WORKER, ROLE_WORKER, ROLE)
                .where(WORKER.ID.eq((int) (long) findByUsername(username).get().getId()))
                .and(ROLE_WORKER.WORKER_ID.eq(WORKER.ID))
                .and(ROLE_WORKER.ROLE_ID.eq(ROLE.ID))
                .fetchInto(Role.class);

    }


}
