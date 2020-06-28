package com.fib.springbootjavers.service.impl;

import com.fib.springbootjavers.service.AuditService;
import org.javers.core.Javers;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class AuditServiceImpl implements AuditService {

    private final Javers javers;

    public AuditServiceImpl(Javers javers) {
        this.javers = javers;
    }

    @Override
    public List<CdoSnapshot> getSnapshotByCommitProperty(Map<String, String> parameters) {
        QueryBuilder queryBuilder = getQueryBuilder(parameters);
        List<CdoSnapshot> snapshots = javers.findSnapshots(queryBuilder.build());
        Collections.reverse(snapshots);
        return snapshots;
    }

    @Override
    public String getSnapshotByCommitPropertyAsString(Map<String, String> parameters) {
        QueryBuilder queryBuilder = getQueryBuilder(parameters);
        List<CdoSnapshot> snapshots = javers.findSnapshots(queryBuilder.build());
        Collections.reverse(snapshots);
        return javers.getJsonConverter().toJson(snapshots);
    }

    private QueryBuilder getQueryBuilder(Map<String, String> parameters){
        QueryBuilder queryBuilder = QueryBuilder.anyDomainObject();

        if(parameters != null){
            parameters.forEach((key, value) -> {
                if(value != null) queryBuilder.withCommitProperty(key, String.valueOf(value));
            });
        }
        return queryBuilder;
    }
}
