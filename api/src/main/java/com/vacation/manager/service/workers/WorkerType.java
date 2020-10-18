package com.vacation.manager.service.workers;

import com.vacation.manager.model.Worker;

public interface WorkerType {

    Worker addEmployee(Worker worker);

    String getStrategyName();



}
