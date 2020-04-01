package cn.dbdj1201.goods.web.service;

/**
 * @author tyz1201
 * @datetime 2020-03-30 22:32
 **/
public interface IGoodsHtmlService {

    /**
     * 创建html页面
     *
     * @param spuId
     * @throws Exception
     */
    void createHtml(Long spuId);

    /**
     * 新建线程处理页面静态化
     *
     * @param spuId
     */
    void asyncExecute(Long spuId);

    void deleteHtml(Long id);
}

