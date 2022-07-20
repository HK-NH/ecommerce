package hk.ecommerce.services.impl;

import hk.ecommerce.entities.AppRole;
import hk.ecommerce.repositories.AppRoleRepository;
import hk.ecommerce.services.AppRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppRoleServiceImpl implements AppRoleService {

    private final AppRoleRepository appRoleRepository;

    public AppRoleServiceImpl(AppRoleRepository appRoleRepository) {
        this.appRoleRepository = appRoleRepository;
    }

    @Override
    public void addRole(AppRole appRole) {
        appRoleRepository.save(appRole);
    }

    @Override
    public List<AppRole> getListRoles() {
        return appRoleRepository.findAll();
    }
}
