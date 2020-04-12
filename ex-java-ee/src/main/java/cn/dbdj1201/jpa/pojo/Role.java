package cn.dbdj1201.jpa.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-04-12 14:13
 **/
@Entity
@Table(name = "tb_role")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;            //角色id
    @Column(name = "role_name")
    private String roleName;        //角色名

    @ManyToMany
    @JoinTable(name = "user_role",//中间表的名称
            //中间表user_role_rel字段关联sys_role表的主键字段role_id
            joinColumns = {@JoinColumn(name = "rid", referencedColumnName = "role_id")},
            //中间表user_role_rel的字段关联sys_user表的主键user_id
            inverseJoinColumns = {@JoinColumn(name = "uid", referencedColumnName = "id")}
    )
    private List<User> users;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
