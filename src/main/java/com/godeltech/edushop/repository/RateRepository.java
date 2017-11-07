package com.godeltech.edushop.repository;

import com.godeltech.edushop.model.Item;
import com.godeltech.edushop.model.Rate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Dmitry on 07.11.2017.
 */
@Repository
public interface RateRepository extends CrudRepository<Rate, Long> {
    Rate findFirst1ByOrderByDateRateDesc();
}
