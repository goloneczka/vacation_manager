package com.vacation.manager.service.workers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WorkerContext {

    private Map<String, WorkerType> strategies;

    public WorkerContext(Set<WorkerType> strategySet) {
        strategies = new HashMap<String, WorkerType>();
        strategySet.forEach(
                strategy -> strategies.put(strategy.getStrategyName(), strategy));
    }

    public WorkerType findStrategy(String strategyName) {
        return strategies.get(strategyName);
    }

}
