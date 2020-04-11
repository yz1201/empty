package cn.dbdj1201.jpa.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author tyz1201
 * @datetime 2020-04-11 16:48
 * 解决实体管理器工厂的资源浪费问题
 **/
public class JpaUtilsNew {
    private static EntityManagerFactory entityManagerFactory;

    static {
        JpaUtilsNew.entityManagerFactory = Persistence.createEntityManagerFactory("myJpa");
    }

    //获取实体类的管理
    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
