package com.fib.springbootjavers.controller;

import com.fib.springbootjavers.domain.Employee;
import com.fib.springbootjavers.service.EmployeeService;
import org.javers.core.Javers;
import org.javers.core.diff.Change;
import org.javers.core.diff.Diff;
import org.javers.core.json.JsonConverter;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "employees", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    private final EmployeeService employeeService;
    private final Javers javers;

    public EmployeeController(EmployeeService employeeService, Javers javers) {
        this.employeeService = employeeService;
        this.javers = javers;
    }

    @GetMapping
    public ResponseEntity<?> getEmployees(){
        return ResponseEntity.ok(employeeService.fetchEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable(value = "id") int id){
        return ResponseEntity.ok(employeeService.findEmployee(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeService.save(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable(value = "id") int id, @RequestBody Employee employee){
        return ResponseEntity.ok(employeeService.update(employee));
    }

    @GetMapping("/changes")
    public String getEmployeeChanges() {
        QueryBuilder jqlQuery = QueryBuilder.byClass(Employee.class);

        List<Change> changes = javers.findChanges(jqlQuery.build());

        return javers.getJsonConverter().toJson(changes);
    }

    @GetMapping("/snapshots")
    public String getEmployeeSnapshots() {
        QueryBuilder jqlQuery = QueryBuilder.byClass(Employee.class);

        List<CdoSnapshot> changes = new ArrayList(javers.findSnapshots(jqlQuery.build()));

        changes.sort((o1, o2) -> -1 * o1.getCommitMetadata().getCommitDate().compareTo(o2.getCommitMetadata().getCommitDate()));

        JsonConverter jsonConverter = javers.getJsonConverter();

        return jsonConverter.toJson(changes);
    }

    @GetMapping("/{localId}/snapshots")
    public String getEmployeeSnapshots(@PathVariable String localId) {
        QueryBuilder jqlQuery = QueryBuilder.byInstanceId(localId, Employee.class);

        List<CdoSnapshot> changes = javers.findSnapshots(jqlQuery.build());

        changes.sort((o1, o2) -> -1 * o1.getCommitMetadata().getCommitDate().compareTo(o2.getCommitMetadata().getCommitDate()));

        JsonConverter jsonConverter = javers.getJsonConverter();

        return jsonConverter.toJson(changes);
    }

    @GetMapping("/{previousId}/diff/{currentId}")
    public String getEmployeeSnapshots(@PathVariable Long previousId, @PathVariable Long currentId) {
        Employee oldObj = employeeService.findEmployee(previousId);
        Employee newObj = employeeService.findEmployee(currentId);

        Diff diff = javers.compare(oldObj, newObj);

//        TODO:
//        List<Change> changes = diff.getChanges(input ->
//                (input instanceof NewObject
//                        && input.getAffectedGlobalId().getCdoClass().getClientsClass() != Employee.class));


        JsonConverter jsonConverter = javers.getJsonConverter();

        return jsonConverter.toJson(diff.getChanges());
    }
}
