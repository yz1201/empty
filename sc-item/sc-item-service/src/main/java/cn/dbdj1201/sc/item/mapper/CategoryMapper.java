package cn.dbdj1201.sc.item.mapper;

import cn.dbdj1201.sc.item.pojo.Category;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-03-17 12:09
 **/
public interface CategoryMapper extends Mapper<Category>, SelectByIdListMapper<Category, Long> {

    @Select("select category_id from tb_category_brand where brand_id = #{bid}")
    List<Long> queryCategoryIdByBrandID(Long bid);
}
