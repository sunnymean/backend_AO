package com.example.payroll.controller;

// import org.springframework.hateoas.EntityModel;
// import org.springframework.web.bind.annotation.*;

import com.example.payroll.exception.EmployeeNotFoundException;
import com.example.payroll.repository.EmployeeRepository;
import com.example.payroll.model.Employee;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Employees", description = "Employee 컨트롤러")
@RestController
@RequiredArgsConstructor
public class EmployeeController {

  private final EmployeeRepository repository;

  /*public EmployeeController(EmployeeRepository repository) {
    this.repository = repository;
  }*/

  @Operation(summary = "get employees", description = "모든 employees 조회 샘플", tags = "Employees", responses = {
      @ApiResponse(responseCode = "200", description = "**오류 발생시 Status Code**<br /><br />" +
          "**MANDATORY_PARAM_ERROR** : mandatory parameter is empty.<br />" +
          "**EXTERNAL_SERVICE_ERROR** : external service error.<br />" +
          "**INTERNAL_SERVER_ERROR** : internal server error.<br />" + "**SUCCESS** : success.<br />" +
          "**FAIL** : fail<br />")})
  @GetMapping(path = "/employees", produces = {MediaType.APPLICATION_JSON_VALUE})
  List<Employee> all() {
    return repository.findAll();
  }

  @Operation(summary = "create a employee", description = "employee 입력 샘플", tags = "Employees", responses = {
      @ApiResponse(responseCode = "200", description = "**오류 발생시 Status Code**<br /><br />" +
          "**MANDATORY_PARAM_ERROR** : mandatory parameter is empty.<br />" +
          "**EXTERNAL_SERVICE_ERROR** : external service error.<br />" +
          "**INTERNAL_SERVER_ERROR** : internal server error.<br />" + "**SUCCESS** : success.<br />" +
          "**FAIL** : fail<br />")})
  @PostMapping("/employees")
  Employee newEmployee(@Parameter(name = "Employee Object",
      description = "object of new employees") @RequestBody @Valid Employee newEmployee) {
    return repository.save(newEmployee);
  }

  @Operation(summary = "get a employee", description = "id 를 이용하여 employee 레코드를 조회합니다.", tags = "Employees",
      responses = {@ApiResponse(responseCode = "200", description = "employee 조회 성공")})
  @GetMapping("/employees/{id}")
  public @ResponseBody Employee one(
      @Parameter(name = "id", description = "employee's id", example = "") @PathVariable(name = "id") final Long id) {
    //    EntityModel<Employee> one(@PathVariable Long id) {
    return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    /* Employee employee = repository.findById(id)
            .orElseThrow(() -> new EmployeeNotFoundException(id));

    return EntityModel.of(employee, //
            linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
            linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));*/

  }

  @Operation(summary = "insert or update a employee", description = "id 를 이용하여 기존 employee를 업데이트 하거나, 없으면 신규 등록.",
      tags = "Employees", responses = {@ApiResponse(responseCode = "200", description = "employee update/create 성공")})
  @PutMapping(value = "/employees/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public @ResponseBody Employee replaceEmployee(@RequestBody @Valid Employee newEmployee,
      @PathVariable(name = "id") Long id) {
    return repository.findById(id).map(employee -> {
      employee.setName(newEmployee.getName());
      employee.setRole(newEmployee.getRole());
      return repository.save(employee);
    }).orElseGet(() -> {
      newEmployee.setId(id);
      return repository.save(newEmployee);
    });
  }

  @Operation(summary = "delete a employee", description = "id 를 이용하여 기존 employee를 삭제 한다", tags = "Employees",
      responses = {@ApiResponse(responseCode = "200", description = "employee terminate 성공")})
  @DeleteMapping("/employees/{id}")
  public void deleteEmployee(
      @Parameter(name = "id", description = "employee's id", example = "2") @PathVariable Long id) {
    repository.deleteById(id);
  }
}
