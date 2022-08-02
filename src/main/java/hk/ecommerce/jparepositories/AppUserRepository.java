package hk.ecommerce.jparepositories;

import hk.ecommerce.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {

    @Query("select u from AppUser u where u.username = :username")
    Optional<AppUser> findAppUserByUsername(@Param("username") String username);
}
