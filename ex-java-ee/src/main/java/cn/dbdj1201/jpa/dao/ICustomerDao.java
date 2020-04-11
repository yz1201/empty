package cn.dbdj1201.jpa.dao;

import cn.dbdj1201.jpa.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author tyz1201
 * @datetime 2020-04-11 18:03
 * JpaRepository<实体类类型，主键类型>：用来完成基本CRUD操作
 * JpaSpecificationExecutor<实体类类型>：用于复杂查询（分页等查询操作）
 **/
public interface ICustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
}
