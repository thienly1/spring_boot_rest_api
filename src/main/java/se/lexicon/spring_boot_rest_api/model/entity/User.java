package se.lexicon.spring_boot_rest_api.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "users")
public class User {

    @Id
    @Column(unique = true, updatable = false)
    private String userName;
    @Column(nullable = false)
    private String password;
    private String expired;

    //Uni- directional Many-To -Many
    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinTable(name = "USERS_ROLES", joinColumns = @JoinColumn(name = "USERNAME"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles;

    //CONVENIENCE METHOD
    public void addRole(Role role){
        if(role==null) throw new IllegalArgumentException("Role is null");
        if(roles==null) roles= new HashSet<>();

        roles.add(role);
    }
    public void removeRole(Role role){
        if(role==null) throw new IllegalArgumentException("Role is null");
        if(roles.contains(role)) roles.remove(role);
    }


}
