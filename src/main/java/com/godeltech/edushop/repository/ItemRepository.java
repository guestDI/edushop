package com.godeltech.edushop.repository;

import com.godeltech.edushop.model.Item;
import com.godeltech.edushop.model.Role;
import com.godeltech.edushop.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Dmitry on 08.10.2017.
 */
@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

}
