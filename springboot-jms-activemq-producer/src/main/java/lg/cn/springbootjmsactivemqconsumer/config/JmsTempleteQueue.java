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
 * 点对点模式
 */
@Component
public class JmsTempleteQueue {

    @Autowired
    JmsTemplate jmsTemplate;

    public void send() {
        jmsTemplate.send("springboot-JmsTempleteQueue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createTextMessage("使用jmsTemlate测试修改消息头");//创建文本消息，还可以创建很多类型的消息
                message.setJMSCorrelationID("1000");//使生产者和消费者进行消息联系
                message.setJMSPriority(7);//设置了也无效, 消息的优先级,0-4为普通的优化级，而5-9为高优先级，通常情况下，高优化级的消息需要优先发送
                message.setJMSMessageID("10000");//设置了也无效,因为这个设置会由消息中间件定制。消息ID，需要以ID:开头
                return message;
            }
        });
    }

}
