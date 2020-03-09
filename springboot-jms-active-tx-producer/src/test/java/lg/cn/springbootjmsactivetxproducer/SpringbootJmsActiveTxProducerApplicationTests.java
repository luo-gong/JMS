package lg.cn.springbootjmsactivetxproducer;

import lg.cn.springbootjmsactivetxproducer.config.ActivemqTx;
import lg.cn.springbootjmsactivetxproducer.config.SpringbootActivemqTx;
import lg.cn.springbootjmsactivetxproducer.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.jms.JMSException;

@SpringBootTest
class SpringbootJmsActiveTxProducerApplicationTests {

    @Autowired
    ActivemqTx activemqTx;

    @Autowired
    MessageService messageService;

    /**
     * 测试没有开启事务时发送消息
     */
    @Test
    void noTx() {
        activemqTx.sendNoTx();
    }

    /**
     * 测试原生方式开启消息事务时发送消息
     */
    @Test
    void tx() throws JMSException {
        activemqTx.sendTx();
    }

    /**
     * 测试springboot注解方式开启消息事务时发送消息
     */
    @Test
    void springbootTx() throws JMSException {
        messageService.sendMessage();
    }


}
