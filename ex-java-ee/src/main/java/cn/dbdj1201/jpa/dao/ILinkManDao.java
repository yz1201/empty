package cn.dbdj1201.jpa.dao;

import cn.dbdj1201.jpa.pojo.LinkMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author tyz1201
 * @datetime 2020-04-12 11:35
 **/
public interface ILinkManDao extends JpaRepository<LinkMan, Long>, JpaSpecificationExecutor<LinkMan> {
}
