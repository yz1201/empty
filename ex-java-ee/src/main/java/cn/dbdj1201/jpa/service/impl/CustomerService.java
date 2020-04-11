package cn.dbdj1201.jpa.service.impl;

import cn.dbdj1201.jpa.dao.ICustomerDao;
import cn.dbdj1201.jpa.pojo.Customer;
import cn.dbdj1201.jpa.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author tyz1201
 * @datetime 2020-04-11 18:15
 **/
@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerDao customerDao;


    @Override
    public void save(Customer customer) {
        customerDao.save(customer);
    }

    @Override
    public Customer findById(Long id) {
        if (customerDao.findById(id).isPresent())
            return customerDao.findById(id).get();
        return null;
    }

    @Override
    public void updateCustomer(Customer customer) {
//        if (customer.getCustId() != null) {
//            customerDao.save(customer);
//        } else {
//            RuntimeException runtimeException = new RuntimeException("数据有问题");
//            runtimeException.printStackTrace();
//        }

        if (customer.getCustId() == null)
            throw new RuntimeException("数据出了点问题");
        customer = this.findById(customer.getCustId());
        this.customerDao.save(customer);
    }

    @Override
    public void deleteById(Long id) {
        customerDao.deleteById(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }
}
