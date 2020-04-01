package cn.dbdj1201.sc.item.service.impl;

import cn.dbdj120.sc.common.pojo.PageResult;
import cn.dbdj1201.sc.item.bo.SpuBo;
import cn.dbdj1201.sc.item.mapper.*;
import cn.dbdj1201.sc.item.pojo.Sku;
import cn.dbdj1201.sc.item.pojo.Spu;
import cn.dbdj1201.sc.item.pojo.SpuDetail;
import cn.dbdj1201.sc.item.pojo.Stock;
import cn.dbdj1201.sc.item.service.IGoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tyz1201
 * @datetime 2020-03-20 23:05
 **/
@Service
public class GoodsService implements IGoodsService {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SpuDetailMapper spuDetailMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsService.class);

    /**
     * @param key      查询关键字，从title字段查
     * @param saleable 是否上架，true up false down 默认为空，意思是全部(up+down)
     * @param page     当前页码
     * @param rows     每页记录数
     * @return 返回自己定义的分页结果集
     */
    @Override
    public PageResult<SpuBo> querySpuByPage(String key, Boolean saleable, Integer page, Integer rows) {
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        //条件查询
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("title", "%" + key + "%");
        }
        //是否上架，还是默认全部
        if (saleable != null)
            criteria.andEqualTo("saleable", saleable);
        //分页查询条件
        PageHelper.startPage(page, rows);
        //分页查询
        List<Spu> spus = spuMapper.selectByExample(example);
        //分页结果
        PageInfo<Spu> pageInfo = new PageInfo<>(spus);
        //封装到自己的分页结果集
        PageResult<SpuBo> result = new PageResult<>();
        //spu集合转化为spubo集合，区别在分类名称跟品牌名称
        List<SpuBo> spuBos = new ArrayList<>();

        spus.forEach(spu -> {
            SpuBo temp = new SpuBo();
            BeanUtils.copyProperties(spu, temp);
            List<String> cNames = this.categoryService.queryNamesByCids(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
            String bName = this.brandMapper.selectByPrimaryKey(spu.getBrandId()).getName();
            temp.setCname(StringUtils.join(cNames, "/"));
            temp.setBname(bName);
            spuBos.add(temp);
        });
        //自己的分页结果集填充数据
        result.setItems(spuBos);
        result.setTotal(pageInfo.getTotal());
        result.setTotalPage(pageInfo.getPages());
        return result;
    }

    @Override
    public void saveGoods(SpuBo spuBo) {
//        spuBo.setId(null);
        spuBo.setSaleable(true);
        spuBo.setValid(true);
        spuBo.setCreateTime(new Date());
        spuBo.setLastUpdateTime(spuBo.getCreateTime());
        this.spuMapper.insert(spuBo);

        // 新增spuDetail
        SpuDetail spuDetail = spuBo.getSpuDetail();
        spuDetail.setSpuId(spuBo.getId());
        this.spuDetailMapper.insertSelective(spuDetail);
        saveSkuAndStock(spuBo);
        //发送生产insert消息
        this.sendMessage(spuBo.getId(), "insert");
    }

    @Override
    public SpuDetail querySpuDetailBySpuId(Long spuId) {
        return this.spuDetailMapper.selectByPrimaryKey(spuId);
    }

    @Override
    public List<Sku> querySkusBySpuId(Long spuId) {
        Sku sku = new Sku();
        sku.setSpuId(spuId);
        return this.skuMapper.select(sku).stream().peek(sku1 ->
                sku1.setStock(stockMapper.selectByPrimaryKey(sku1.getId()).getStock())).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateGoods(SpuBo spuBo) {
        // 查询以前sku
        List<Sku> skus = this.querySkusBySpuId(spuBo.getId());
        // 如果以前存在，则删除
        if (!CollectionUtils.isEmpty(skus)) {
            List<Long> ids = skus.stream().map(Sku::getId).collect(Collectors.toList());
            // 删除以前库存
            Example example = new Example(Stock.class);
            example.createCriteria().andIn("skuId", ids);
            this.stockMapper.deleteByExample(example);

            // 删除以前的sku
            Sku record = new Sku();
            record.setSpuId(spuBo.getId());
            this.skuMapper.delete(record);
        }
        // 新增sku和库存
        saveSkuAndStock(spuBo);

        // 更新spuBo
        spuBo.setLastUpdateTime(new Date());
        spuBo.setCreateTime(null);
        spuBo.setValid(null);
        spuBo.setSaleable(null);
        this.spuMapper.updateByPrimaryKeySelective(spuBo);
        // 更新spuBo详情
        this.spuDetailMapper.updateByPrimaryKeySelective(spuBo.getSpuDetail());

        //发送生产update消息
        this.sendMessage(spuBo.getId(), "update");
    }

    @Override
    public Spu querySpuById(Long id) {
        return this.spuMapper.selectByPrimaryKey(id);
    }

    private void saveSkuAndStock(SpuBo spuBo) {
        spuBo.getSkus().forEach(sku -> {
            // 新增sku
            sku.setSpuId(spuBo.getId());
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            this.skuMapper.insertSelective(sku);

            // 新增库存
            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            this.stockMapper.insertSelective(stock);
        });
    }

    private void sendMessage(Long id, String type) {
        // 发送消息
        try {
            this.amqpTemplate.convertAndSend("item." + type, id);
        } catch (Exception e) {
            LOGGER.error("{}商品消息发送异常，商品id：{}", type, id, e);
        }
    }


}
