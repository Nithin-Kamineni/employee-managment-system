package net.javaguides.departmentservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        description = "DepartmentDto Model Information"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

    @Schema(
            description = "Department id"
    )
    private Long id;

    @Schema(
            description = "Department Name"
    )
    private String departmentName;

    @Schema(
            description = "Department Description"
    )
    private String departmentDescription;

    @Schema(
            description = "Department Code"
    )
    private String departmentCode;
}
