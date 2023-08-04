package net.javaguides.employeeservice.service.impl;

//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
//import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import net.javaguides.employeeservice.dto.APIResponseDto;
//import net.javaguides.employeeservice.dto.DepartmentDto;
import net.javaguides.employeeservice.dto.DepartmentDto;
import net.javaguides.employeeservice.dto.EmployeeDto;
//import net.javaguides.employeeservice.dto.OrganizationDto;
import net.javaguides.employeeservice.dto.OrganizationDto;
import net.javaguides.employeeservice.entity.Employee;
//import net.javaguides.employeeservice.mapper.EmployeeMapper;
import net.javaguides.employeeservice.exception.ResourceNotFoundException;
import net.javaguides.employeeservice.mapper.AutoEmployeeMapper;
import net.javaguides.employeeservice.repository.EmployeeRepository;
//import net.javaguides.employeeservice.service.APIClient;
import net.javaguides.employeeservice.service.APIClient;
import net.javaguides.employeeservice.service.APIClientOrg;
import net.javaguides.employeeservice.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;
//import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private EmployeeRepository employeeRepository;

//    private RestTemplate restTemplate;

//    private WebClient webClient;
    private APIClient apiClient;
    private APIClientOrg apiClientOrg;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

//        Employee employee = new Employee(
//                employeeDto.getId(),
//                employeeDto.getFirstName(),
//                employeeDto.getLastName(),
//                employeeDto.getEmail(),
//                employeeDto.getDepartmentCode()
//        );

        Employee employee = AutoEmployeeMapper.MAPPER.mapToEmployee(employeeDto);


        Employee saveDEmployee = employeeRepository.save(employee);

//        EmployeeDto savedEmployeeDto = new EmployeeDto(
//                saveDEmployee.getId(),
//                saveDEmployee.getFirstName(),
//                saveDEmployee.getLastName(),
//                saveDEmployee.getEmail(),
//                employeeDto.getDepartmentCode()
//        );

        EmployeeDto savedEmployeeDto = AutoEmployeeMapper.MAPPER.mapToEmployeeDto(saveDEmployee);

        return savedEmployeeDto;
    }

//    @CircuitBreaker(name="${spring.application.name}", fallbackMethod="getDefaultDepartment")
    @Retry(name="${spring.application.name}", fallbackMethod="getDefaultDepartment")
    @Override
    public APIResponseDto getEmployeeById(Long employeeId) throws ResourceNotFoundException {
        LOGGER.info("inside getEmployeeById() method");
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if(employee.isEmpty()){
            throw new ResourceNotFoundException("Employee","ID",employeeId);
        }

//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.get().getDepartmentCode(), DepartmentDto.class);
//
//        DepartmentDto departmentDto = responseEntity.getBody();

//        DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8080/api/departments/" + employee.get().getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();

        DepartmentDto departmentDto = apiClient.getDepartmentByCode(employee.get().getDepartmentCode());
//        System.out.println("---------------------------------------------------------------------------------");
//        System.out.println(employee.get().getOrganizationCode());
//        System.out.println("---------------------------------------------------------------------------------");

        OrganizationDto organizationDto = apiClientOrg.getOrganizationByCode(employee.get().getOrganizationCode());
//        System.out.println(organizationDto);
        //        OrganizationDto organizationDto = webClient.get()
//                .uri("http://localhost:8083/api/organizations/" + employee.get().getOrganizationCode())
//                .retrieve()
//                .bodyToMono(OrganizationDto.class)
//                .block();

//        EmployeeDto employeeDto = new EmployeeDto(
//                employee.get().getId(),
//                employee.get().getFirstName(),
//                employee.get().getLastName(),
//                employee.get().getEmail(),
//                employee.get().getDepartmentCode()
//        );

        EmployeeDto employeeDto = AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee.get());

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        apiResponseDto.setOrganization(organizationDto);

        return apiResponseDto;
    }

    public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception) throws ResourceNotFoundException{
        LOGGER.info("inside getDefaultDepartment() method");
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if(employee.isEmpty()){
            throw new ResourceNotFoundException("Employee","ID",employeeId);
        }

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and Development department");

//        EmployeeDto employeeDto = new EmployeeDto(
//                employee.get().getId(),
//                employee.get().getFirstName(),
//                employee.get().getLastName(),
//                employee.get().getEmail(),
//                employee.get().getDepartmentCode()
//        );
        EmployeeDto employeeDto = AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee.get());

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }
}