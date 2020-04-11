package cn.dbdj1201.jpa.service;

import cn.dbdj1201.jpa.pojo.Customer;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-04-11 18:14
 **/
public interface ICustomerService {

    void save(Customer customer);

    Customer findById(Long id);

    void updateCustomer(Customer customer);

    void deleteById(Long id);

    List<Customer> findAll();

}
