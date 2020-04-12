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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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
        l.setLkmName("TBD联系人");
        l.setLkmGender("m");
        l.setLkmMobile("13811111111");
        l.setLkmPhone("010-34785348");
        l.setLkmEmail("98354834@qq.com");
        l.setLkmPosition("老师");
        l.setLkmMemo("还行吧");

        c.getLinkmans().add(l);
        l.setCustomer(c);
        customerDao.save(c);
        linkManDao.save(l);
    }
}
