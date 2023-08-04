package net.javaguides.employeeservice.service;
import net.javaguides.employeeservice.dto.OrganizationDto;
import net.javaguides.employeeservice.exception.ResourceNotFoundException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ORGANIZATION-SERVICE")
public interface APIClientOrg {
    @GetMapping("api/organizations/{organization-code}")
    OrganizationDto getOrganizationByCode(@PathVariable("organization-code") String organizationCode) throws ResourceNotFoundException;

//    @GetMapping("api/organizations/{organization-code}")
//    DepartmentDto getOrganizationByCode(@PathVariable("organization-code") String organizationCode) throws ResourceNotFoundException;
}
