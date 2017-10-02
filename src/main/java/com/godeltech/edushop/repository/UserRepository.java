package com.godeltech.edushop.repository;

import com.godeltech.edushop.model.Role;
import com.godeltech.edushop.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by d.ihnatovich on 10/2/2017.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
