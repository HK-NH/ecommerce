package hk.ecommerce.services;

import hk.ecommerce.entities.AppRole;

import java.util.List;

public interface AppRoleService {

    void save(AppRole appRole);
    List<AppRole> getListRoles();
}
