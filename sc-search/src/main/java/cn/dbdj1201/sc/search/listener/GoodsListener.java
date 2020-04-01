package cn.dbdj1201.sc.search.listener;

import cn.dbdj1201.sc.search.service.ISearchService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tyz1201
 * @datetime 2020-04-01 23:44
 **/
@Component
public class GoodsListener {

    @Autowired
    private ISearchService searchService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "sc.create.index.queue", durable = "true"),
            exchange = @Exchange(
                    value = "sc.item.exchange",                 //交换机
                    ignoreDeclarationExceptions = "true",       //
                    type = ExchangeTypes.TOPIC                  //交换机路由匹配模式
            ),
            key = {"item.insert", "item.update"}))
    public void listenCreate(Long id) throws Exception {
        if (id == null)
            return;
        this.searchService.createIndex(id);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "sc.delete.index.queue", durable = "true"),
            exchange = @Exchange(
                    value = "sc.item.exchange",                 //交换机
                    ignoreDeclarationExceptions = "true",       //
                    type = ExchangeTypes.TOPIC                  //交换机路由匹配模式
            ),
            key = "item.delete"))
    public void listenDelete(Long id) {
        if (id == null)
            return;
        this.searchService.deleteIndex(id);
    }
}
