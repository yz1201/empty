package cn.dbdj1201.jpa.service.impl;

import cn.dbdj1201.jpa.dao.ICustomerDao;
import cn.dbdj1201.jpa.pojo.Customer;
import cn.dbdj1201.jpa.service.ICustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


/**
 * @author tyz1201
 * @datetime 2020-04-11 18:18
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerServiceTest {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ICustomerDao customerDao;

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
//        this.customerService.findAll().forEach(System.out::println);
//        this.customerDao.findAllCustomers().forEach(System.out::println);
//        System.out.println(this.customerDao.findCount());
//        this.customerDao.findCustomersByPage(1, 2).forEach(System.out::println);
//        this.customerDao.findByCustNameLike("%asd%").forEach(System.out::println);

        Specification<Customer> spec = (Specification<Customer>) (root, query, criteriaBuilder) -> {
            //cb:构建查询，添加查询方式   like：模糊匹配
            //root：从实体Customer对象中按照custName属性进行查询
            return criteriaBuilder.like(root.get("custName").as(String.class), "asd%");
        };


        /*
         * 构造分页参数
         * 		Pageable : 接口
         * 			PageRequest实现了Pageable接口，调用构造方法的形式构造
         * 				第一个参数：页码（从0开始）
         * 				第二个参数：每页查询条数
         */
        Pageable pageable = PageRequest.of(0, 5);
        this.customerDao.findAll(spec, pageable).forEach(System.out::println);
    }
}