package cn.dbdj1201.jpa.service.impl;

import cn.dbdj1201.jpa.pojo.Customer;
import cn.dbdj1201.jpa.service.ICustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author tyz1201
 * @datetime 2020-04-11 18:18
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerServiceTest {

    @Autowired
    private ICustomerService customerService;

    @Test
    public void save() {
    }

    @Test
    public void findById() {
        System.out.println(this.customerService.findById(11L));
    }

    @Test
    public void updateCustomer() {
        Customer customer = new Customer();
//        customer.setCustId(2L);
        customer.setCustName("yeshijie");
        this.customerService.updateCustomer(customer);
    }

    @Test
    public void deleteById() {
        this.customerService.deleteById(2L);
    }

    @Test
    public void findAll() {
//        System.out.println(this.customerService.findAll());
        this.customerService.findAll().forEach(System.out::println);
    }
}