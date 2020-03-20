package cn.dbdj1201.sc.item.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-03-20 11:17
 **/
@Table(name = "tb_spec_group")
public class SpecGroup implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cid;

    private String name;

    @Transient
    private List<SpecParam> params;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SpecParam> getParams() {
        return params;
    }

    public void setParams(List<SpecParam> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "SpecGroup{" +
                "id=" + id +
                ", cid=" + cid +
                ", name='" + name + '\'' +
                ", params=" + params +
                '}';
    }
}

