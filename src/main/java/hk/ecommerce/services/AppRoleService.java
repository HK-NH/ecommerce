package hk.ecommerce.services;

import hk.ecommerce.entities.AppRole;

import java.util.List;

public interface AppRoleService {

    void addRole(AppRole appRole);
    List<AppRole> getListRoles();
}
