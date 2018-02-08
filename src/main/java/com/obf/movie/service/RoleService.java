package com.obf.movie.service;


import com.obf.movie.domain.Role;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleService {

    private final PersonService personService;
    private final RoleTypeService roleTypeService;

    public RoleService(PersonService personService, RoleTypeService roleTypeService) {
        this.personService = personService;
        this.roleTypeService = roleTypeService;
    }

    List<Role> setRoleWithDataFromDB(List<Role> roles) {
        for(Role item: roles){
            getPersonFromDB(item);
            getRoleTypeFromDB(item);
        }
        return roles;
    }

    private void getRoleTypeFromDB(Role item) {
        item.setRoleType(roleTypeService.getRoleTypeFromDB(item.getRoleType()));
    }

    private void getPersonFromDB(Role item) {
        item.setPerson(personService.getPersonFromDB(item.getPerson()));
    }


}
