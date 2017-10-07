package com.godeltech.edushop.repository;

import com.godeltech.edushop.model.Role;
import com.godeltech.edushop.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
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
    public List<User> findExceptAdmin();

}
