package cn.dbdj1201.goods.web.listener;

import cn.dbdj1201.goods.web.service.IGoodsHtmlService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tyz1201
 * @datetime 2020-04-01 23:56
 **/
@Component
public class GoodsListener {

    @Autowired
    private IGoodsHtmlService goodsHtmlService;

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "sc.create.web.queue", durable = "true"),
            exchange = @Exchange(
                    value = "sc.item.exchange",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC
            ),
            key = {"item.insert", "item.update"}))
    public void ListenerCreate(Long id) throws Exception {
        if (id == null) {
            return;
        }
        // 创建页面
        goodsHtmlService.createHtml(id);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "sc.delete.web.queue", durable = "true"),
            exchange = @Exchange(
                    value = "sc.item.exchange",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC
            ),
            key = "item.delete"))
    public void ListenerDelete(Long id) throws Exception {
        if (id == null) {
            return;
        }
        // 删除页面
        goodsHtmlService.deleteHtml(id);
    }
}
