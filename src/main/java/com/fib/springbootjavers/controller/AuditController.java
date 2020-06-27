package com.fib.springbootjavers.controller;

import com.fib.springbootjavers.service.AuditService;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "audits", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuditController {

    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @PostMapping("/snapshot")
    public List<CdoSnapshot> getSnapshot(@RequestBody Map<String,String> parameters){
        return auditService.getSnapshotByCommitProperty(parameters);
    }

    @PostMapping("/snapshot-as-string")
    public String getSnapshotAsString(@RequestBody Map<String,String> parameters){
        return auditService.getSnapshotByCommitPropertyAsString(parameters);
    }
}
