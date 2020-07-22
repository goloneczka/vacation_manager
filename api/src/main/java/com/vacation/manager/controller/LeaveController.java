package com.vacation.manager.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.vacation.manager.model.PaidLeave;
import com.vacation.manager.model.api.PaidLeaveApi;
import com.vacation.manager.model.api.WorkerApi;
import com.vacation.manager.service.LeaveService;
import com.vacation.manager.service.WorkersService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/leaves")
public class LeaveController {

    private final LeaveService leaveService;
    private final ModelMapper modelMapper;

    public LeaveController(LeaveService leaveService, ModelMapper modelMapper) {
        this.leaveService = leaveService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/{mail}/{enterprise}")
    public ResponseEntity<List<WorkerApi>> getWorkerLeaves(
            @PathVariable String mail, @PathVariable String enterprise) {

        return ResponseEntity.ok()
                .body(modelMapper.map(leaveService.getWorkerLeaves(mail, enterprise),
                        new TypeReference<List<PaidLeaveApi>>() {}.getType()));
    }

    @PostMapping(value = "/add/{mail}/{enterprise}")
    public ResponseEntity<PaidLeaveApi> addWorkerLeaves(@Valid @RequestBody PaidLeaveApi paidLeaveApi,
                                                        @PathVariable String mail, @PathVariable String enterprise) {
        PaidLeave paidLeave = modelMapper.map(paidLeaveApi, PaidLeave.class);
        return ResponseEntity.ok()
                .body(modelMapper.map(leaveService.createWorkerLeaves(paidLeave, mail, enterprise), PaidLeaveApi.class));
    }

}
