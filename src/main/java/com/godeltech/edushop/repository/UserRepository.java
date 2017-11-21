package com.godeltech.edushop.repository;

import com.godeltech.edushop.dto.UserProfileDTO;
import com.godeltech.edushop.model.Role;
import com.godeltech.edushop.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;

/**
 * Created by d.ihnatovich on 10/2/2017.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("Select u from User u where u.role.id != 1")
    List<User> findExceptAdmin();

    @Query("Select count(*) from User u where u.role.id != 1")
    int findNumberOfUsersExceptAdmin();

    @Modifying
    @Query("UPDATE User u SET u.firstname = :#{#dto.firstname}, u.lastname = :#{#dto.lastname}, u.email = :#{#dto.email}, u.profilePhoto = :#{#dto.profilePhoto}" +
            " WHERE u.id = :#{#dto.id}")
    int update(@Param("dto") UserProfileDTO dto);

    @Modifying(clearAutomatically = true)
    @Query("Update User u SET u.active = :status WHERE u.id = :id")
    Integer updateUserStatus(@Param("id") Long userId, @Param("status") boolean status);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.role.id = :roleId WHERE u.id = :id")
    Integer updateUserRole(@Param("id") Long userId, @Param("roleId") Long roleId);

    @Query("Select u from User u where u.username = :username AND u.password = :password")
    User login(@Param("username") String username, @Param("password") String password);
}
