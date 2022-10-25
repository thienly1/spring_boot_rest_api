package se.lexicon.spring_boot_rest_api.model.dto;

import lombok.*;
import se.lexicon.spring_boot_rest_api.model.entity.Role;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
public class UserDto {

    @NotBlank
    @Size(min = 2, max = 60, message = "must between 2 and 60 characters")
    private String username;

    @NotBlank
    @Size(min = 8, max = 30, message = "must be more than 8 character")
    private String password;
    private boolean expired;

    @NotNull
    @Valid //Active Validation in RoleDto (If you want to have it validated
    private List<RoleDto> roles;
}
