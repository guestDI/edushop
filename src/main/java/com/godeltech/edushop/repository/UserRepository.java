package com.godeltech.edushop.repository;

import com.godeltech.edushop.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Dmitry on 30.09.2017.
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
