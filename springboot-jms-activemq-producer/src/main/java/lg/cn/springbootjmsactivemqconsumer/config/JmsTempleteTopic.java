package lg.cn.springbootjmsactivemqconsumer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * 使用JmsTemplete对发送消息
 * 发布订阅模式
 */
@Component
public class JmsTempleteTopic {
    @Autowired
    JmsTemplate jmsTemplate;


    public void send() {
        jmsTemplate.send("springboot-JmsTempleteTopic", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createTextMessage("使用jmsTemlate测试修改消息头");
                message.setJMSCorrelationID("20000");
                return message;
            }
        });
    }


}
