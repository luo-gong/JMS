package lg.cn.springbootjmsactivemqconsumer;

import lg.cn.springbootjmsactivemqconsumer.config.JmsMessagingTemplateQueue;
import lg.cn.springbootjmsactivemqconsumer.config.JmsMessagingTemplateTopic;
import lg.cn.springbootjmsactivemqconsumer.pojo.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class JmsMessagingTemplateTests {

    @Autowired
    JmsMessagingTemplateQueue jmsMessagingTemplateQueue;

    @Autowired
    JmsMessagingTemplateTopic jmsMessagingTemplateTopic;

    @Test
    void queueTest() {
        Message message = new Message();
        message.setContent("hello queueTest");
        message.setDate(new Date());
        jmsMessagingTemplateQueue.send(message);
    }

    @Test
    void topicTest() {
        Message message = new Message();
        message.setContent("hello topicTest");
        message.setDate(new Date());
        jmsMessagingTemplateTopic.send(message);
    }

}
