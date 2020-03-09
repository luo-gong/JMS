package lg.cn.springbootjmsactivetxproducer.service;

import lg.cn.springbootjmsactivetxproducer.config.SpringbootActivemqTx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import java.util.HashMap;
import java.util.Map;

/**
 * 业务类
 */
@Service
public class MessageService {

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    JmsTemplate jmsTemplate;

    /**
     * 将需要添加事务的消息加上 @Transactional发送的消息就会开启事务
     */
    @Transactional
    public void sendMessage() {
        for (int i = 0; i < 5; i++) {
            //模拟发生异常
           /* if (i == 3) {
                throw new RuntimeException("springboot注解方式开启消息事务测试");
            }*/
//            jmsMessagingTemplate.convertAndSend("springbootActivmq", "使用springboot添加消息注解方式添加消开启事务");
            jmsTemplate.send("springbootActivmq", new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    MapMessage message = session.createMapMessage();
                    message.setString("message", "MapMessage");
                    return message;
                }
            });
        }
    }
}
