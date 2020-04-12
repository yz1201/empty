package cn.dbdj1201.jpa.dao;

import cn.dbdj1201.jpa.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author tyz1201
 * @datetime 2020-04-12 14:21
 **/
public interface IRoleDao extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
}
