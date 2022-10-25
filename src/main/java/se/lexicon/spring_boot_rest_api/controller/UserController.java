package se.lexicon.spring_boot_rest_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.spring_boot_rest_api.exception.ResourceNotFoundException;
import se.lexicon.spring_boot_rest_api.model.dto.CustomUserDto;
import se.lexicon.spring_boot_rest_api.model.dto.UserDto;
import se.lexicon.spring_boot_rest_api.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserDto userDto){ //@Valid TRIGGERS VALIDATION ON ANNOTATED @RequestBody before the method runs
        System.out.println("userDto = " + userDto);
        UserDto result = userService.register(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);

        //manually catching the Exception

//        UserDto result = null;
//        try{
//            result= userService.register(userDto);
//            return ResponseEntity.status(HttpStatus.CREATED).body(result);
//        }catch (ResourceNotFoundException ex){
//            ex.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<CustomUserDto> findByUsername(@PathVariable("username") String username) throws ResourceNotFoundException{
        return null;
    };

    @PutMapping("/disable")
    public ResponseEntity<Void> disableUser(@RequestParam("username") String username) throws ResourceNotFoundException{
        return null;
    };

    @PutMapping("/enable")
    public ResponseEntity<Void> enableUser(@RequestParam("username") String username) throws ResourceNotFoundException{
        return null;
    }
}
