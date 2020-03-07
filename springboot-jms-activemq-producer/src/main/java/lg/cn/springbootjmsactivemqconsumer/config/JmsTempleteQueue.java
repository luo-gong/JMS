package lg.cn.springbootjmsactivemqconsumer.config;

import lg.cn.springbootjmsactivemqconsumer.pojo.Pepole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 使用JmsTemplete对发送消息
 * 点对点模式
 */
@Component
public class JmsTempleteQueue {

    @Autowired
    JmsTemplate jmsTemplate;

    /**
     * 发送文本消息
     */
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

    /**
     * 发送Map消息MapMessage
     */
    public void send1() {
        jmsTemplate.send("springboot-JmsTempleteQueue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage message = session.createMapMessage();
                message.setString("name", "张三");
                message.setInt("age", 20);
                return message;
            }
        });
    }

    /**
     * 发送对象消息ObjectMessage
     * 注意对象必须序列化
     * 注意生产者Pepole的和消费者的People类路径要一样并且要设置spring.activemq.packages.trust-all=true,不然消费者在监听时会报找不到Pepole的错误
     */
    public void send2() {
        jmsTemplate.send("springboot-JmsTempleteQueue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage message = session.createObjectMessage();
                Pepole pepole = new Pepole();
                pepole.setName("张三");
                pepole.setAge(20);
                message.setObject(pepole);
                return message;
            }
        });
    }

    /**
     * 发送字节消息（比如发送图片）BytesMessage
     */
    public void send3() {
        jmsTemplate.send("BytesMessage", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                BytesMessage message = session.createBytesMessage();
                try {
                    //1.读取文件
                    File file = new File("D:/Users/luogong/Desktop/idea-background/641.png");
                    //2.构建文件输入流
                    FileInputStream fileInputStream = new FileInputStream(file);
                    //3.将输入流写入到缓存数组中
                    byte[] bytes = new byte[(int) file.length()];
                    fileInputStream.read(bytes);
                    //4.把缓存数组写入到message
                    message.writeBytes(bytes);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return message;
            }
        });
    }

}
