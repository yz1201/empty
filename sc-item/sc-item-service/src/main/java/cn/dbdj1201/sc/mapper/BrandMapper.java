package cn.dbdj1201.sc.mapper;

import cn.dbdj1201.sc.pojo.Brand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author tyz1201
 * @datetime 2020-03-17 22:32
 **/
public interface BrandMapper extends Mapper<Brand> {

    @Update("insert into tb_category_brand (category_id, brand_id) values (#{cid}, #{bid})")
    void addCategoryAndBrand(@Param("cid") Long cid, @Param("bid") Long bid);
}
