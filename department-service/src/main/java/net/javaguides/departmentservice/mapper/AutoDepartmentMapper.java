package net.javaguides.departmentservice.mapper;
import net.javaguides.departmentservice.dto.DepartmentDto;
import net.javaguides.departmentservice.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoDepartmentMapper {

    AutoDepartmentMapper MAPPER = Mappers.getMapper(AutoDepartmentMapper.class);

    //    @Mapping(source = "email", target = "emailAddress")
    DepartmentDto mapToDepartmentDto(Department user);
    Department mapToDepartment(DepartmentDto userDto);
}
