package lg.cn.springbootjmsactivemqconsumer;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import javax.jms.Queue;
import javax.jms.Topic;


@SpringBootApplication
public class JsmActivemqApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsmActivemqApplication.class, args);
    }

    @Order(Integer.MIN_VALUE)
    @Bean
    public Queue queue() {
        return new ActiveMQQueue("springboot-queue");
    }

    @Bean
    public Topic topic() {
        return new ActiveMQTopic("springboot-topic");
    }
}
