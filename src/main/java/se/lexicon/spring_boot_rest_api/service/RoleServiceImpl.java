package se.lexicon.spring_boot_rest_api.service;

import ch.qos.logback.core.subst.Token;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.spring_boot_rest_api.exception.ResourceNotFoundException;
import se.lexicon.spring_boot_rest_api.model.dto.RoleDto;
import se.lexicon.spring_boot_rest_api.model.entity.Role;
import se.lexicon.spring_boot_rest_api.repository.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RoleDto create(RoleDto form) {
        Role toEntity = modelMapper.map(form, Role.class);
        Role savedRole= repository.save(toEntity);
        return modelMapper.map(savedRole, RoleDto.class);
    }

    @Override
    public void update(RoleDto form, Integer id) {
        if(id==null) throw new IllegalArgumentException("Id is null");
        if(form==null) throw new IllegalArgumentException(("RoleDto is null"));
        if(!id.equals(form.getId())) throw new IllegalArgumentException("Id needs to match");
        Role role= modelMapper.map(form, Role.class);
        repository.save(role);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<RoleDto> findAll() {
        return modelMapper.map(repository.findAll(), new TypeToken<List<RoleDto>>(){}.getType());
    }

    @Override
    public RoleDto findById(Integer id) {
        if(id==null) throw new IllegalArgumentException("Id is null");
        Role foundById = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Role data not found"));
        return modelMapper.map(foundById, RoleDto.class);
    }

    @Override
    public RoleDto findByName(String name) {
        if(name==null) throw new IllegalArgumentException("name is null");
        Role foundByName = repository.findByName(name).orElseThrow(()->new ResourceNotFoundException("Role data not found"));
        return modelMapper.map(foundByName, RoleDto.class);
    }
}
