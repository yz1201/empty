package cn.dbdj1201.sc.auth.entity;

import java.io.Serializable;

/**
 * @author tyz1201
 * @datetime 2020-04-02 23:55
 **/
public class UserInfo implements Serializable {
    private Long id;

    private String username;

    public UserInfo() {
    }

    public UserInfo(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
