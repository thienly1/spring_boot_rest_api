package se.lexicon.spring_boot_rest_api.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import se.lexicon.spring_boot_rest_api.exception.ResourceDuplicateException;
import se.lexicon.spring_boot_rest_api.exception.ResourceNotFoundException;
import se.lexicon.spring_boot_rest_api.model.dto.CustomUserDto;
import se.lexicon.spring_boot_rest_api.model.dto.RoleDto;
import se.lexicon.spring_boot_rest_api.model.dto.UserDto;
import se.lexicon.spring_boot_rest_api.model.entity.Role;
import se.lexicon.spring_boot_rest_api.model.entity.User;
import se.lexicon.spring_boot_rest_api.repository.RoleRepository;
import se.lexicon.spring_boot_rest_api.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    private  final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository=roleRepository;
    }

    @Override
    public UserDto register(UserDto userDto) throws ResourceNotFoundException {
        //TODO
        //Check if the input data is
        if(userDto==null) throw new IllegalArgumentException("User Dto is null");
        if(userDto.getUsername()==null) throw new IllegalArgumentException("Username can not be null");
        if(userDto.getPassword()==null) throw new IllegalArgumentException("Password can not be null");
        if(userDto.isExpired()) throw new IllegalArgumentException("Expired date must be false or null");
        if(userDto.getRoles()==null||userDto.getRoles().size()==0) throw new IllegalArgumentException("No Roles found");

        //Check if the username is a duplicate
        if(userRepository.existsByUserName(userDto.getUsername())) throw new ResourceDuplicateException("Username is already in use");
        //Check if Roles are valid Roles
        for(RoleDto roleDto: userDto.getRoles()){
            roleRepository.findById(roleDto.getId()).orElseThrow(()->new ResourceNotFoundException("Role ID is not valid"));
        }
        //Convert Dto to an Entity UserDto ->User + RoleDto ->Role
        User convertedToEntity = modelMapper.map(userDto, User.class);
        //Save entity to database
        User createdUser= userRepository.save(convertedToEntity);
        //Convert Entity to Dto
        UserDto convertedToDto= modelMapper.map(createdUser, UserDto.class);
        return convertedToDto;
    }

    @Override
    public CustomUserDto findByUsername(String username) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public void disableUserByUsername(String username) throws ResourceNotFoundException {

    }

    @Override
    public void enableUserByUsername(String username) throws ResourceNotFoundException {

    }
}
