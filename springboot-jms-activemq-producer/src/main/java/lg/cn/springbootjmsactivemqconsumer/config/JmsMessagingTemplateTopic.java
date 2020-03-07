package lg.cn.springbootjmsactivemqconsumer.config;

import lg.cn.springbootjmsactivemqconsumer.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * 使用JmsMessagingTemplate对发送消息
 * 发布订阅模式
 */
@Component
public class JmsMessagingTemplateTopic {

    @Autowired
    Topic topic;

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    public void send(Message message) {
        jmsMessagingTemplate.convertAndSend(topic, message);//或者
//        jmsMessagingTemplate.convertAndSend("springboot-topic", "你好springboot 集成activemq 生产者");//发送转换消息并且发送消息
    }

}
