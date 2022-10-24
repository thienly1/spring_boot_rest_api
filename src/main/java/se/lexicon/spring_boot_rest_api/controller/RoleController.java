package se.lexicon.spring_boot_rest_api.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.spring_boot_rest_api.exception.ResourceNotFoundException;
import se.lexicon.spring_boot_rest_api.model.dto.RoleDto;
import se.lexicon.spring_boot_rest_api.model.entity.Role;
import se.lexicon.spring_boot_rest_api.repository.RoleRepository;
import se.lexicon.spring_boot_rest_api.service.RoleService;

import java.util.List;


@RestController
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService= roleService;
    }

    @GetMapping("/api/v1/roles")
    public ResponseEntity<List<RoleDto>> findAll(){

        System.out.println("###  Get Roles has been executed  ###");
//        List<Role> listOfRole= repository.findAll();
//        List<RoleDto> listOfDto= new ArrayList<>();
//        for(Role role:listOfRole){
//            listOfDto.add(new RoleDto(role.getId(), role.getName()));
//        }
//        List<RoleDto> listOfDto= modelMapper.map(listOfRole, new TypeToken<List<RoleDto>>(){}.getType());

//        return ResponseEntity.status(HttpStatus.OK).body(listOfDto);
//        return ResponseEntity.status(200).body(listOfDto),
        return ResponseEntity.ok(roleService.findAll());
    }


    @GetMapping("/api/v1/roles/{id}")
    public ResponseEntity<RoleDto> findByRoleId(@PathVariable("id") Integer id){

//        Optional<Role> foundById= repository.findById(id);  //1, 3
//        RoleDto roleDto= new RoleDto(foundById.get().getId(), foundById.get().getName());//1
//        if(foundById.isPresent()){                      //2
//            return ResponseEntity.ok(roleDto.get());    //2
//        }else {                                             //2
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();//2
//        }


//        return foundById.map(ResponseEntity::ok)       //1, 3
//                .orElseGet(()->ResponseEntity.status(HttpStatus.NO_CONTENT).build()); //3 //optional type can use map function, but object type can not

//        return ResponseEntity.ok(roleDto); //1
//        if(id==null) throw new IllegalArgumentException("Id is null");
//        Role foundById= repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Role date was not found"));
        return ResponseEntity.ok(roleService.findById(id));
    }

    @GetMapping("/api/v1/roles/search")
    public ResponseEntity<RoleDto> findByRoleName(@RequestParam("name") String name) {

//        Optional<Role> foundByName = repository.findByName(name);
//        RoleDto roleDto= new RoleDto(foundByName.get().getId(), foundByName.get().getName());
//        if (foundByName.isPresent()) {
//            return ResponseEntity.ok(foundByName.get());
//        } else {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        }
//        return ResponseEntity.ok(roleDto);
//        if(name==null) throw new IllegalArgumentException("name is null");
//        Role foundByName = repository.findByName(name).orElseThrow(RuntimeException::new);
        return ResponseEntity.ok(roleService.findByName(name));
    }

    @PostMapping("/api/v1/roles")
    public ResponseEntity<RoleDto> create(@RequestBody RoleDto formDto){
        System.out.println("###### In Create method");

//        Role toEntity= modelMapper.map(formDto, Role.class);
//        Role savedRole= repository.save(toEntity);
//        RoleDto toDto= modelMapper.map(savedRole, RoleDto.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.create(formDto));
    }

    @PutMapping("/api/v1/roles/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id, @RequestBody RoleDto roleDto){
        System.out.println("########### update method  ");
        System.out.println("id = " + id);
        System.out.println("roleFormDto = " + roleDto);
//        if(id.equals(roleDto.getId())){
//            Role role= modelMapper.map(roleDto,Role.class);
//            repository.save(role);
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        }else {
//            return ResponseEntity.status(418).build();
//        }
        roleService.update(roleDto,id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/api/v1/roles/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        System.out.println("id = " + id);
//        repository.deleteById(id);
        roleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
