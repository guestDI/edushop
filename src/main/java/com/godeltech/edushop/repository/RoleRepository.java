package com.godeltech.edushop.repository;

import com.godeltech.edushop.model.Role;
import com.godeltech.edushop.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by d.ihnatovich on 9/29/2017.
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{

}
