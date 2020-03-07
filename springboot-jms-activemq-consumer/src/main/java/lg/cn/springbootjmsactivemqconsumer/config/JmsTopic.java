package lg.cn.springbootjmsactivemqconsumer.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lg.cn.springbootjmsactivemqconsumer.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * 主题模式
 * 发布订阅
 */
@Component
public class JmsTopic {

    /**
     * 配置监听主题
     *
     * @param message
     * @throws JMSException
     * @throws JsonProcessingException
     */
    @JmsListener(destination = "springboot-JmsTempleteTopic")
    public void receive(Object message) throws JMSException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        if (message == null) {
            System.out.println("接收的消息为空");
        } else if (message instanceof TextMessage) {
            System.out.println(((TextMessage) message).getText());
        } else if (message instanceof ObjectMessage) {
            objectMapper.writeValueAsString(((ObjectMessage) message).getObject());
        } else {
            System.out.println(objectMapper.writeValueAsString(message));//其他消息类型
        }
    }
}
