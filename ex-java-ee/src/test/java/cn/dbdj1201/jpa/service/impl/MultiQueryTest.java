package cn.dbdj1201.jpa.service.impl;

import cn.dbdj1201.jpa.dao.ICustomerDao;
import cn.dbdj1201.jpa.dao.ILinkManDao;
import cn.dbdj1201.jpa.pojo.Customer;
import cn.dbdj1201.jpa.pojo.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-04-12 11:36
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MultiQueryTest {

    @Autowired
    private ICustomerDao customerDao;

    @Autowired
    private ILinkManDao linkManDao;

    @Test
    public void testQuery() {
//        System.out.println(this.customerDao.findById(14L));
        this.customerDao.findById(14L).get().getLinkmans().forEach(System.out::println);
    }

    @Test
    @Transactional
    @Rollback(false)//设置为不回滚
    public void testAdd() {
        Customer c = new Customer();
        c.setCustName("TBD云集中心");
        c.setCustLevel("VIP客户");
        c.setCustSource("网络");
        c.setCustIndustry("商业办公");
        c.setCustAddress("昌平区北七家镇");
        c.setCustPhone("010-84389340");


        LinkMan l = new LinkMan();
        l.setLkmName("TBD联系人2");
        l.setLkmGender("m");
        l.setLkmMobile("13811121111");
        l.setLkmPhone("010-347185348");
        l.setLkmEmail("983542834@qq.com");
        l.setLkmPosition("老师");
        l.setLkmMemo("还行吧");
        l.setCustomer(this.customerDao.findById(16L).get());

        c.getLinkmans().add(l);
        l.setCustomer(c);
        customerDao.save(c);
        linkManDao.save(l);

//        this.customerDao.deleteById(16L);
//        this.linkManDao.deleteById(9L);
    }

    @Test
    @Transactional
    @Rollback(false)//设置为不回滚
    public void testFind() {
//        System.out.println(this.customerDao.findById(17L).get());
        this.customerDao.findById(17L).get().getLinkmans().forEach(System.out::println);
    }


    /**
     * Specification的多表查询
     */
    @Test
    public void testFind1() {
        Specification<LinkMan> spec = new Specification<LinkMan>() {
            public Predicate toPredicate(Root<LinkMan> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //Join代表链接查询，通过root对象获取
                //创建的过程中，第一个参数为关联对象的属性名称，第二个参数为连接查询的方式（left，inner，right）
                //JoinType.LEFT : 左外连接,JoinType.INNER：内连接,JoinType.RIGHT：右外连接
                Join<LinkMan, Customer> join = root.join("customer", JoinType.INNER);
                return cb.like(join.get("custName").as(String.class), "TBD%");
            }
        };
        List<LinkMan> list = linkManDao.findAll(spec);
        for (LinkMan linkMan : list) {
            System.out.println(linkMan);
        }
    }
}
