package hk.ecommerce.jparepositories;

import hk.ecommerce.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    @Query("select r from AppRole r where r.roleName = ?1")
    Optional <AppRole> findAppRoleByRoleName(String roleName);
}
