package cn.dbdj1201.sc.item.bo;

import cn.dbdj1201.sc.item.pojo.Sku;
import cn.dbdj1201.sc.item.pojo.Spu;
import cn.dbdj1201.sc.item.pojo.SpuDetail;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-03-20 23:09
 **/
public class SpuBo extends Spu {
    private String cname;
    private String bname;
    private SpuDetail spuDetail;// 商品详情
    private List<Sku> skus;// sku列表

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public SpuDetail getSpuDetail() {
        return spuDetail;
    }

    public void setSpuDetail(SpuDetail spuDetail) {
        this.spuDetail = spuDetail;
    }

    public List<Sku> getSkus() {
        return skus;
    }

    public void setSkus(List<Sku> skus) {
        this.skus = skus;
    }

    @Override
    public String toString() {
        return "SpuBo{" +
                "cName='" + cname + '\'' +
                ", bName='" + bname + '\'' +
                ", spuDetail=" + spuDetail +
                ", skus=" + skus +
                '}';
    }
}
