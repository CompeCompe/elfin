package ru.eremenko.elfin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.eremenko.elfin.entity.User;


/**
 * @author eremenko
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select exists( select * from \"user\" u where lower(u.username) = lower(:username) or lower(u.email) = lower(:email))", nativeQuery = true)
    boolean checkExistenceUsersWithSameEmailOrUsername(@Param("username") String username, @Param("email") String email);

}
