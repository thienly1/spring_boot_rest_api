package se.lexicon.spring_boot_rest_api.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomUserDto {

    private String username;
    private List<RoleDto> roles;

}
