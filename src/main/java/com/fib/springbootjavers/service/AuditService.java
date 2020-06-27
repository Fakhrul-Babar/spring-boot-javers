package com.fib.springbootjavers.service;

import org.javers.core.metamodel.object.CdoSnapshot;

import java.util.List;
import java.util.Map;

public interface AuditService {

    List<CdoSnapshot> getSnapshotByCommitProperty(Map<String, String> parameters);

    String getSnapshotByCommitPropertyAsString(Map<String, String> parameters);
}
