package cn.dbdj1201.jpa.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-04-11 14:26
 **/
@Entity
@Table(name = "tb_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;     //用户id
    @Column(name = "name")
    private String name;    //用户姓名
    @Column(name = "age")
    private String age;     //用户年龄
    @ManyToMany(mappedBy = "users")
    private List<Role> roles; //

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
