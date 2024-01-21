package com.shehanrathnayake.api;

import com.shehanrathnayake.service.custom.EmployeeService;
import com.shehanrathnayake.to.EmployeeTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@CrossOrigin
public class EmployeeHttpController {

    private final EmployeeService service;

    public EmployeeHttpController(EmployeeService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public EmployeeTO crateNewEmployee(@RequestBody @Validated EmployeeTO employeeTO) {
        return service.saveEmployee(employeeTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{employee-id}", consumes = "application/json")
    public void updateEmployee(@PathVariable("employee-id") String employeeId,
                            @RequestBody @Validated EmployeeTO employeeTO) {
        employeeTO.setId(employeeId);
        service.updateEmployee(employeeTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{employee-id}")
    public void deleteEmployee(@PathVariable("employee-id") String employeeId) {
        service.deleteEmployee(employeeId);
    }

    @GetMapping(value = "/{employee-id}", produces = "application/json")
    public EmployeeTO getEmployeeDetails(@PathVariable("employee-id") String employeeId) {
        return service.getEmployeeDetails(employeeId);
    }

    @GetMapping(produces = "application/json")
    public List<EmployeeTO> getAllEmployees() {
        return service.getAllEmployees();
    }
}
