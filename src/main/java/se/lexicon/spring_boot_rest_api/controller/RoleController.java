package se.lexicon.spring_boot_rest_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.spring_boot_rest_api.model.dto.RoleDto;
import se.lexicon.spring_boot_rest_api.model.entity.Role;
import se.lexicon.spring_boot_rest_api.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RoleController {

    private RoleRepository repository;

    @Autowired
    public RoleController(RoleRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/v1/roles")
    public ResponseEntity<List<RoleDto>> findAll(){

        System.out.println("###  Get Roles has been executed  ###");
        List<Role> listOfRole= repository.findAll();
        List<RoleDto> listOfDto= new ArrayList<>();
        for(Role role:listOfRole){
            listOfDto.add(new RoleDto(role.getId(), role.getName()));
        }

//        return ResponseEntity.status(HttpStatus.OK).body(listOfDto);
//        return ResponseEntity.status(200).body(listOfDto),
        return ResponseEntity.ok(listOfDto);
    }


    @GetMapping("/api/v1/roles/{id}")
    public ResponseEntity<RoleDto> findByRoleId(@PathVariable("id") Integer id){

        Optional<Role> foundById= repository.findById(id);
        RoleDto roleDto= new RoleDto(foundById.get().getId(), foundById.get().getName());
//        if(foundById.isPresent()){
//            return ResponseEntity.ok(roleDto.get());
//        }else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }

        //return no data
//        System.out.println(id);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

//        return foundById.map(ResponseEntity::ok)
//                .orElseGet(()->ResponseEntity.status(HttpStatus.NO_CONTENT).build());  //optional type can use map function, but object type can not

        return ResponseEntity.ok(roleDto);
    }

    @GetMapping("/api/v1/roles/search")
    public ResponseEntity<RoleDto> findByRoleName(@RequestParam("name") String name) {

        Optional<Role> foundByName = repository.findByName(name);
        RoleDto roleDto= new RoleDto(foundByName.get().getId(), foundByName.get().getName());
//        if (foundByName.isPresent()) {
//            return ResponseEntity.ok(foundByName.get());
//        } else {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        }
        return ResponseEntity.ok(roleDto);
    }

    @PostMapping("/api/v1/roles")
    public ResponseEntity<Role> create(@RequestBody RoleDto formDto){
        System.out.println("###### In Create method");
        Role role= new Role(formDto.getId(), formDto.getName());
        Role savedRole = repository.save(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRole);
    }

    @PutMapping("/api/v1/roles/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id, @RequestBody RoleDto roleDto){
        System.out.println("########### update method  ");
        System.out.println("id = " + id);
        System.out.println("roleFormDto = " + roleDto);
        if(id.equals(roleDto.getId())){
            Role role= new Role(id, roleDto.getName());
            repository.save(role);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else {
            return ResponseEntity.status(418).build();
        }
    }

    @DeleteMapping("/api/v1/roles/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        System.out.println("id = " + id);
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
