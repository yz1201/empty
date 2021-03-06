package cn.dbdj1201.jpa.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author tyz1201
 * @datetime 2020-04-11 15:25
 **/
@Entity //声明实体类
@Table(name = "cst_customer") //建立实体类和表的映射关系
public class Customer implements Serializable {
    @Id//声明当前私有属性为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //配置主键的生成策略
    @Column(name = "cust_id") //指定和表中cust_id字段的映射关系
    private Long custId;            //客户id

    @Column(name = "cust_name") //指定和表中cust_name字段的映射关系
    private String custName;        //客户名称(公司名称)

    @Column(name = "cust_source")//指定和表中cust_source字段的映射关系
    private String custSource;      //客户信息来源

    @Column(name = "cust_industry")//指定和表中cust_industry字段的映射关系
    private String custIndustry;    //客户所属行业

    @Column(name = "cust_level")//指定和表中cust_level字段的映射关系
    private String custLevel;       //客户级别

    @Column(name = "cust_address")//指定和表中cust_address字段的映射关系
    private String custAddress;     //客户联系地址

    @Column(name = "cust_phone")//指定和表中cust_phone字段的映射关系
    private String custPhone;       //客户电话

    //配置客户和联系人的一对多关系
//    @OneToMany(targetEntity = LinkMan.class,fetch = FetchType.EAGER)
//    @JoinColumn(name = "lkm_cust_id", referencedColumnName = "cust_id")
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<LinkMan> linkmans = new HashSet<>(0);

    public Set<LinkMan> getLinkmans() {
        return linkmans;
    }

    public void setLinkmans(Set<LinkMan> linkmans) {
        this.linkmans = linkmans;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", custPhone='" + custPhone + '\'' +
                '}';
    }
}
