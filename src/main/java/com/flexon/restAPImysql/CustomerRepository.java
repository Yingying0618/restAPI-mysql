package com.flexon.restAPImysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    List<Customer> findAll();

    @Query(name="findByID",nativeQuery = true,value = "select * from customer where accountID=:accountID")
    List<Customer> findByID(@Param("accountID") Long accountID);
}
