package se.lexicon.spring_boot_rest_api.model.dto;

import lombok.*;
import se.lexicon.spring_boot_rest_api.model.entity.Role;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
public class UserDto {

    private String username;
    private String password;
    private boolean expired;
    private List<RoleDto> roles;
}
