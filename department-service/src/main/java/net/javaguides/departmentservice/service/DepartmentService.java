package net.javaguides.departmentservice.service;

import net.javaguides.departmentservice.dto.DepartmentDto;
import net.javaguides.departmentservice.exception.ResourceNotFoundException;

import java.util.List;

public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentByCode(String departmentCode) throws ResourceNotFoundException;

    List<DepartmentDto> getAllDepartments();
}
