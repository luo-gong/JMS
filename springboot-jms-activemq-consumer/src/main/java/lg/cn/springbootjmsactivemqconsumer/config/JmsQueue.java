package lg.cn.springbootjmsactivemqconsumer.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lg.cn.springbootjmsactivemqconsumer.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class JmsQueue {

    /**
     * 配置监听队列
     *
     * @param message
     * @throws JsonProcessingException
     */
    //    @JmsListener(destination = "${destination}")
    @JmsListener(destination = "springboot-queue")
    public void receive(Object message) throws JsonProcessingException, JMSException {
        ObjectMapper objectMapper = new ObjectMapper();
        if (message == null) {
            System.out.println("接收的消息为空");
        } else if (message instanceof TextMessage) {
            String JMSCorrelationID = ((TextMessage) message).getJMSCorrelationID();
            System.out.println(((TextMessage) message).getText());
            System.out.println("JMSCorrelationID>>>>" + JMSCorrelationID);
        } else if (message instanceof ObjectMessage) {
            System.out.println(objectMapper.writeValueAsString(((ObjectMessage) message).getObject()));
        } else {
            System.out.println(objectMapper.writeValueAsString(message));//其他消息类型
        }
    }

    /**
     * 监听消息类型为BytesMessage（例如传输的文件）
     */
    @JmsListener(destination = "BytesMessage")
    public void receive1(Object message) throws JMSException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        if (message == null) {
            System.out.println("接收的消息为空");
        } else if (message instanceof BytesMessage) {
            BytesMessage bytesMessage = (BytesMessage) message;
            //1.创建缓存数组
            byte[] bytes = new byte[(int) bytesMessage.getBodyLength()];
            //2.将缓存数据写入缓存数组中
            bytesMessage.readBytes(bytes);
            //3.构建文件输出流
            FileOutputStream fileOutputStream = new FileOutputStream("D:/Users/luogong/Desktop/idea-background/test.jpg");
            //4.把数据写入到本地磁盘
            fileOutputStream.write(bytes);
        } else {
            System.out.println(objectMapper.writeValueAsString(message));//其他消息类型
        }
    }
}
