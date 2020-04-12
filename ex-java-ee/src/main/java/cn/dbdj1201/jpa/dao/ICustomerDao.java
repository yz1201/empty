package cn.dbdj1201.jpa.dao;

import cn.dbdj1201.jpa.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-04-11 18:03
 * JpaRepository<实体类类型，主键类型>：用来完成基本CRUD操作
 * JpaSpecificationExecutor<实体类类型>：用于复杂查询（分页等查询操作）
 **/
public interface ICustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    @Query("from Customer ")
    List<Customer> findAllCustomers();

    @Query("select count(1) from Customer ")
    int findCount();

    @Query(value = "select * from cst_customer limit ?1, ?2 ", nativeQuery = true)
    List<Customer> findCustomersByPage(int startRows, int endRows);

    List<Customer> findByCustNameLike(String custName);


}
