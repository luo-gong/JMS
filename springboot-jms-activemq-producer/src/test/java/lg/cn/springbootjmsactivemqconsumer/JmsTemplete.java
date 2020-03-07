package lg.cn.springbootjmsactivemqconsumer;

import lg.cn.springbootjmsactivemqconsumer.config.JmsTempleteQueue;
import lg.cn.springbootjmsactivemqconsumer.config.JmsTempleteTopic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 使用jmsTemplete对消息头进行设置
 */
@SpringBootTest
public class JmsTemplete {

    @Autowired
    JmsTempleteQueue jmsTempleteQueue;

    @Autowired
    JmsTempleteTopic jmsTempleteTopic;

    /**
     * 测试使用JmsTemplete对消息头进行设置
     * 客户端只能设置三个值，其他无效
     * 1.JMSCorrelationID  关联的消息ID，这个通常用在需要回传消息的时候
     * 2.JMSReplyTo  消息回复的目的地，其值为一个Topic或Queue, 这个由发送者设置，但是接收者可以决定是否响应
     * 3.JMSType  由消息发送者设置的个消息类型，代表消息的结构，有的消息中间件可能会用到这个，但这个并不是是批消息的种类，比如
     * TextMessage之类的
     */
    @Test
    void messageHeaderQueue() {
        jmsTempleteQueue.send();
    }


    @Test
    void messageHeaderTopic() {
        jmsTempleteTopic.send();
    }

    @Test
    void BytesMessage() {
        jmsTempleteQueue.send3();
    }
}
