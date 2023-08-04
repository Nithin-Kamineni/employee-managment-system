package net.javaguides.departmentservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.javaguides.departmentservice.dto.DepartmentDto;
import net.javaguides.departmentservice.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Department Service - Department Controller",
        description = "Department Controller Exposes REST APIs for Department-Service"
)
@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;

    @Operation(
            summary = "Save Department REST API",
            description = "Save Department REST API is used to save department object in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartment = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get All Departments REST API",
            description = "Get All Departments REST API is used to save department object in a database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
        List<DepartmentDto> savedDepartments = departmentService.getAllDepartments();
        return new ResponseEntity<>(savedDepartments, HttpStatus.OK);
    }

    @Operation(
            summary = "Get Department REST API",
            description = "Get Department REST API is used to save department object in a database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("{department-code}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable("department-code") String departmentCode) throws Exception {
        DepartmentDto departments = departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }
}