package cn.dbdj1201.sc.mapper;

import cn.dbdj1201.sc.pojo.Category;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-03-17 12:09
 **/
public interface CategoryMapper extends Mapper<Category> {

    @Select("select category_id from tab_category_brand where bid = #{bid}")
    List<Long> queryCategoryIdByBrandID(Long bid);
}
