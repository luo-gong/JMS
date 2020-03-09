package lg.cn.springbootjmsactivetxconsumer.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * 实现消费者开启消息事务
 */
@Component
public class MessageTx {

    /**
     * 开启消息监听，接收消息
     * 添加参数Session 实现消息事务
     *
     * @param message
     * @param session
     */
    @JmsListener(destination = "springbootActivmq")
    public void receive(Object message, Session session) throws JMSException {
        try {
            if (message instanceof TextMessage) {//如果消息时文本消息
                TextMessage textMessage = (TextMessage) message;
                System.out.println(textMessage.getText());
            } else {//如果消息是其他类型
                System.out.println("进行消息的转化");
                throw new RuntimeException("测试消费者事务回滚");
            }
            session.commit();//一旦事务回滚，消息服务器会在发送6次
        } catch (Exception e) {
            session.rollback();
            System.out.println(e.getMessage());
        }
    }


}
