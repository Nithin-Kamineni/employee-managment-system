package net.javaguides.departmentservice.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.departmentservice.dto.DepartmentDto;
import net.javaguides.departmentservice.entity.Department;
import net.javaguides.departmentservice.exception.ResourceNotFoundException;
import net.javaguides.departmentservice.mapper.AutoDepartmentMapper;
import net.javaguides.departmentservice.repository.DepartmentRepository;
import net.javaguides.departmentservice.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        //convert department dto to department jpa entity
        Department department = AutoDepartmentMapper.MAPPER.mapToDepartment(departmentDto);


        Department savedDepartment = departmentRepository.save(department);

        DepartmentDto savedDepartmentDto = AutoDepartmentMapper.MAPPER.mapToDepartmentDto(savedDepartment);

        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) throws ResourceNotFoundException {
        Department departmentOptional = departmentRepository.findByDepartmentCode(departmentCode).orElseThrow(
                () -> new ResourceNotFoundException("Department","Id",departmentCode)
        );
        if(departmentCode.isEmpty()){
            throw new ResourceNotFoundException("Department","id",departmentCode);
        }

        return AutoDepartmentMapper.MAPPER.mapToDepartmentDto(departmentOptional);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments =  departmentRepository.findAll();
        return departments.stream().map(AutoDepartmentMapper.MAPPER::mapToDepartmentDto).collect(Collectors.toList());
    }
}
