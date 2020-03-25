package cn.dbdj1201.sc.item.service.impl;

import cn.dbdj120.sc.common.pojo.PageResult;
import cn.dbdj1201.sc.item.mapper.BrandMapper;
import cn.dbdj1201.sc.item.pojo.Brand;
import cn.dbdj1201.sc.item.service.IBrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tyz1201
 * @datetime 2020-03-17 22:34
 **/
@Service
public class BrandService implements IBrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 根据查询条件分页并排序查询品牌信息
     *
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    @Override
    public PageResult<Brand> queryBrandsByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        // 初始化example对象
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        // 根据name模糊查询，或者根据首字母查询
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("name", "%" + key + "%").orEqualTo("letter", key);
        }

        // 添加分页条件
        PageHelper.startPage(page, rows);

        // 添加排序条件
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }

        List<Brand> brands = this.brandMapper.selectByExample(example);
        // 包装成pageInfo
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);
        // 包装成分页结果集返回
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    @Transactional
    public void addBrand(Brand brand, List<Long> cids) {
        this.brandMapper.insert(brand);
        cids.forEach(cid -> this.brandMapper.addCategoryAndBrand(cid, brand.getId()));
    }

    @Override
    public void delete(Long bid) {
        brandMapper.deleteByPrimaryKey(bid);
    }

    @Override
    public List<Brand> queryBrandsByCid(Long cid) {
        return brandMapper.queryBrandIdsByCid(cid).stream().map(brandId -> brandMapper.selectByPrimaryKey(brandId))
                .collect(Collectors.toList());

    }

    @Override
    public Brand queryBrandById(Long id) {
        return this.brandMapper.selectByPrimaryKey(id);
    }
}
