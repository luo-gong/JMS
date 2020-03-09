package lg.cn.springbootjmsactivetxproducer.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.ConnectionFactory;

/**
 * springboot实现activemq事务
 */
@Component
public class SpringbootActivemqTx {

    /**
     * 添加消息事务管理器
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public PlatformTransactionManager myPlatformTransactionManager(@Qualifier("jmsConnectionFactory") ConnectionFactory connectionFactory) {
        return new JmsTransactionManager(connectionFactory);
    }

}
