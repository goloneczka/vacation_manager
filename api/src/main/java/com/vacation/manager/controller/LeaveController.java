package com.vacation.manager.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.vacation.manager.model.PaidLeave;
import com.vacation.manager.model.api.PaidLeaveApi;
import com.vacation.manager.model.api.WorkerApi;
import com.vacation.manager.model.api.WorkerLeaveApi;
import com.vacation.manager.model.api.WorkerLeaveListApi;
import com.vacation.manager.response.ResponseStatus;
import com.vacation.manager.service.LeaveService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
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

    @GetMapping(value = "/{mail}/{enterpriseName}")
    public ResponseEntity<List<WorkerApi>> getWorkerLeaves(
            @PathVariable String mail, @PathVariable String enterpriseName) {

        return ResponseEntity.ok()
                .body(modelMapper.map(leaveService.getWorkerLeaves(mail, enterpriseName),
                        new TypeReference<List<PaidLeaveApi>>() {
                        }.getType()));
    }


    @GetMapping(value = "/HR/{leaveId}")
    public ResponseEntity<WorkerLeaveApi> getWorkerLeaveDetails(
            @PathVariable Long leaveId) {

        return ResponseEntity.ok()
                .body(leaveService.getWorkerLeaveDeatils(leaveId));
    }


    @GetMapping(value = "/{enterpriseName}")
    public ResponseEntity<List<WorkerLeaveListApi>> getUnresolvedByCompanyName(@PathVariable String enterpriseName) {
        return ResponseEntity.ok()
                .body(modelMapper.map(leaveService.getUnresolvedByCompanyName(enterpriseName),
                        new TypeReference<List<WorkerLeaveListApi>>() {
                        }.getType()));
    }

    @GetMapping(value = "/past/{enterpriseName}/{page}")
    public ResponseEntity<List<WorkerLeaveListApi>> getResolvedByCompanyName(
            @PathVariable String enterpriseName, @PathVariable Integer page) {

        return ResponseEntity.ok()
                .body(modelMapper.map(leaveService.getResolvedByCompanyName(enterpriseName, page),
                        new TypeReference<List<WorkerLeaveListApi>>() {
                        }.getType()));
    }

    @PostMapping(value = "/add/{mail}/{enterpriseName}")
    public ResponseEntity<PaidLeaveApi> createWorkerLeave(@Valid @RequestBody PaidLeaveApi paidLeaveApi,
                                                        @PathVariable String mail, @PathVariable String enterpriseName) {
        PaidLeave paidLeave = modelMapper.map(paidLeaveApi, PaidLeave.class);
        return ResponseEntity.ok()
                .body(modelMapper.map(leaveService.createWorkerLeaves(paidLeave, mail, enterpriseName), PaidLeaveApi.class));
    }

    @PutMapping(value = "/HR/{id}/{status}")
    public ResponseEntity<PaidLeaveApi> setLeaveStatus(@PathVariable Long id, @PathVariable String status) {
        return ResponseEntity.ok()
                .body(modelMapper.map(leaveService.putLeaveStatus(id, status), PaidLeaveApi.class));
    }

    @DeleteMapping(value = "/CEO/{enterpriseName}")
    public ResponseEntity<?> clearLeaves(@PathVariable String enterpriseName) {
        leaveService.deleteOutDated(enterpriseName, LocalDate.now());
        return ResponseEntity
                .status(ResponseStatus.OK)
                .body(new HashMap.SimpleEntry<>("success", true));
    }
}
