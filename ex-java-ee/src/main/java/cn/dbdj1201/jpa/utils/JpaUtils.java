package cn.dbdj1201.jpa.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author tyz1201
 * @datetime 2020-04-11 16:22
 **/
public class JpaUtils {
    private ThreadLocal<EntityManager> threadLocal = new ThreadLocal<>();
    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;

    //获取实体类的管理
    public  EntityManager getEntityManager() {
        if (entityManager == null) {
            entityManager = Persistence.createEntityManagerFactory("myJpa").createEntityManager();
            threadLocal.set(entityManager);
        }
        return threadLocal.get();
    }

    //获取事务
    public EntityTransaction getEntityTransaction() {
        return threadLocal.get().getTransaction();
    }

    /**
     * 资源释放
     */
    public void close() {
        if (this.entityManager != null) {
            this.entityManager.close();

        }

        if (this.entityManagerFactory != null) {
            this.entityManagerFactory.close();
        }
    }


}
