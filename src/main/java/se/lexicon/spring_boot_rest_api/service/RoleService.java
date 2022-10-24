package se.lexicon.spring_boot_rest_api.service;

import se.lexicon.spring_boot_rest_api.model.dto.RoleDto;

import java.util.List;

public interface RoleService {

    RoleDto create(RoleDto form);
    void update(RoleDto form, Integer id);
    void deleteById(Integer id);
    List<RoleDto> findAll();
    RoleDto findById(Integer id);
    RoleDto findByName(String name);
}
