package cn.chen.myspringcloud.streams;

import org.junit.Test;
import org.junit.runner.RunWith;
//import org.springframework.amqp.core.MessageBuilder;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@EnableBinding(value = {SinkReceiverTest.SinkSender.class})
public class SinkReceiverTest {
// 消费单元的单元测试用例
    @Autowired
    private SinkSender sinkSender;

    @Test
    public void sinkSenderTester() {
        sinkSender.output().send(MessageBuilder.withPayload("Testing Message".getBytes()).build());
    }

    public interface SinkSender {
        String OUTPUT = "'ouput ' is input ";

        @Output(SinkSender.OUTPUT)
        MessageChannel output();
    }
}

