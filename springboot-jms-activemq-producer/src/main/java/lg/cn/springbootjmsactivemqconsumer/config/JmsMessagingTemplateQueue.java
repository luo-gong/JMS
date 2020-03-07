package lg.cn.springbootjmsactivemqconsumer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * 使用JmsMessagingTemplate对发送消息
 * 点对点模式
 */
@Component
public class JmsMessagingTemplateQueue {

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    Queue queue;

    public void send(lg.cn.springbootjmsactivemqconsumer.pojo.Message message) {
//        jmsMessagingTemplate.convertAndSend("springboot-queue", message);//springboot-queue为目的
        jmsMessagingTemplate.convertAndSend(queue, message);//或者这样

    }
}
