package net.javaguides.employeeservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Schema(
        description = "Organization Dto Model Information"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto {
    @Schema(
            description = "Organization id"
    )
    private Long id;

    @Schema(
            description = "Organization Name"
    )
    private String organizationName;

    @Schema(
            description = "Organization Description"
    )
    private String organizationDescription;

    @Schema(
            description = "Organization Code"
    )
    private String organizationCode;

    @Schema(
            description = "Organization created Date"
    )
    private LocalDateTime createdDate;
}
