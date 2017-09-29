package com.godeltech.edushop.repository;

import com.godeltech.edushop.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by d.ihnatovich on 9/29/2017.
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{

}
