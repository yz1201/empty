package cn.dbdj1201.jpa.service;

import cn.dbdj1201.jpa.pojo.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.util.concurrent.TimeUnit;

/**
 * @author tyz1201
 * @datetime 2020-04-11 15:51
 **/
public class IUserServiceTest {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    @Before
    public void init() {
        //创建实体管理类
        //获取事务对象
        //开启事务
        this.entityManagerFactory = Persistence.createEntityManagerFactory("myJpa");
        this.entityManager = this.entityManagerFactory.createEntityManager();
        this.entityTransaction = this.entityManager.getTransaction();
        this.entityTransaction.begin();

    }

    @After
    public void destory() {
        //提交事务
        this.entityTransaction.commit();
        //释放资源
        this.entityManager.close();
        this.entityManagerFactory.close();
    }

    @Test
    public void save() {
        /*
         * 创建实体管理类工厂，借助Persistence的静态方法获取
         * 		其中传递的参数为持久化单元名称，需要jpa配置文件中指定
         */
        //持久化
        for (int i = 1; i < 10; i++) {
            Customer c = new Customer();
            c.setCustName("asdasdwasd");
            c.setCustAddress("$!#w%!#%");
            c.setCustIndustry("25w2afaf");
            c.setCustLevel("3w");
            c.setCustPhone("2132w15252");
            c.setCustSource("235w25dada");
            this.entityManager.persist(c);
        }
    }

    @Test
    public void findById() {
//        System.out.println(this.entityManager.find(Customer.class, 2L));
//        Customer reference = this.entityManager.getReference(Customer.class, 2L);
        Customer customer = this.entityManager.find(Customer.class, 2L);
        System.out.println("sleeping 2s");
        /*
            getReference:
                lazy init
                获取的对象是一个动态代理对象，正常啊，不这样咋lazy init啊？
         */
//        System.out.println(this.entityManager.getReference(Customer.class, 2L));
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("awake");
        System.out.println(customer);
    }

    @Test
    public void updateUser() {
        Customer customer = this.entityManager.find(Customer.class, 2L);
        customer.setCustAddress("he nan kai feng");
        this.entityManager.merge(customer);
    }

    @Test
    public void deleteById() {
        Customer customer = new Customer();
        customer.setCustName("传智播客");
//        Customer customer1 = this.entityManager.find(Customer.class, 1L);
        Customer customer2 = this.entityManager.find(Customer.class, 2L);

        System.out.println(customer2);
//        this.entityManager.remove(customer1);
    }

    @Test
    public void findAll() {
//        String jpql = "from Customer where custName like ?1";
//        String jpql = "select count(1) from Customer";
        String jpql = "from Customer order by custId desc";
        Query query = this.entityManager.createQuery(jpql);
//        query.getResultList().forEach(System.out::println);

//        query.setParameter(1,"%asd%");

//        System.out.println(query.getSingleResult());

//        query.setFirstResult(0);
//        query.setMaxResults(5);
        query.getResultList().forEach(System.out::println);

    }
}