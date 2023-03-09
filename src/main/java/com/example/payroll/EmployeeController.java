package com.example.payroll;

//import org.springframework.hateoas.EntityModel;
//import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "Employees", description = "Employee 컨트롤러")
@RestController
public class EmployeeController {

    private final EmployeeRepository repository;


    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "get employees", description = "모든 employees 조회 샘플", tags = "Employees", responses =
            {@ApiResponse(responseCode = "200", description = "**오류 발생시 Status Code**<br /><br />" +
                    "**MANDATORY_PARAM_ERROR** : mandatory parameter is empty.<br />" + "**EXTERNAL_SERVICE_ERROR** :" +
                    " external service error.<br />" + "**INTERNAL_SERVER_ERROR** : internal server error.<br />" +
                    "**SUCCESS** : success.<br />" + "**FAIL** : fail<br />")})
    @GetMapping(path = "/employees", produces = {MediaType.APPLICATION_JSON_VALUE})
    List<Employee> all() {
        return repository.findAll();
    }

    @Operation(summary = "create a employee", description = "employee 입력 샘플", tags = "Employees", responses =
            {@ApiResponse(responseCode = "200", description = "**오류 발생시 Status Code**<br /><br />" +
                    "**MANDATORY_PARAM_ERROR** : mandatory parameter is empty.<br />" + "**EXTERNAL_SERVICE_ERROR** :" +
                    " external service error.<br />" + "**INTERNAL_SERVER_ERROR** : internal server error.<br />" +
                    "**SUCCESS** : success.<br />" + "**FAIL** : fail<br />")})
    @PostMapping("/employees")
    Employee newEmployee(@Parameter(name = "Employee Object", description = "object of new employees", example =
            "589HE3") @RequestBody Employee newEmployee) {
        return repository.save(newEmployee);
    }

    @Operation(summary = "get a employee", description = "one employee 조회 샘플", tags = "Employees", responses =
            {@ApiResponse(responseCode = "200", description = "**오류 발생시 Status Code**<br /><br />" +
                    "**MANDATORY_PARAM_ERROR** : mandatory parameter is empty.<br />" + "**EXTERNAL_SERVICE_ERROR** :" +
                    " external service error.<br />" + "**INTERNAL_SERVER_ERROR** : internal server error.<br />" +
                    "**SUCCESS** : success.<br />" + "**FAIL** : fail<br />")})
    @GetMapping("/employees/{id}")
    Employee one(@Parameter(name = "Employee Id", description = "id number of a employee", example =
            "2") @PathVariable(name="Employee id") long id) {
//    EntityModel<Employee> one(@PathVariable Long id) {
        return repository.findById((long) id).orElseThrow(() -> new EmployeeNotFoundException(id));
       /* Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return EntityModel.of(employee, //
                linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));*/

    }

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return repository.findById(id).map(employee -> {
            employee.setName(newEmployee.getName());
            employee.setRole(newEmployee.getRole());
            return repository.save(employee);
        }).orElseGet(() -> {
            newEmployee.setId(id);
            return repository.save(newEmployee);
        });
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
