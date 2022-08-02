package hk.ecommerce.jparepositories;

import hk.ecommerce.entities.RegistrationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationTokenRepository extends JpaRepository<RegistrationToken,Long> {

    @Query("select t from RegistrationToken t where t.token = :token")
    RegistrationToken findVerificationTokenByToken(@Param("token") String token);
}
