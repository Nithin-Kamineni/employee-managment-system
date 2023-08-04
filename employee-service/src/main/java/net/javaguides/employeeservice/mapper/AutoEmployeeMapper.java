package net.javaguides.employeeservice.mapper;

import net.javaguides.employeeservice.dto.EmployeeDto;
import net.javaguides.employeeservice.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoEmployeeMapper {
    AutoEmployeeMapper MAPPER = Mappers.getMapper(AutoEmployeeMapper.class);

    //    @Mapping(source = "email", target = "emailAddress")
    EmployeeDto mapToEmployeeDto(Employee user);
    Employee mapToEmployee(EmployeeDto EmployeeDto);
}
