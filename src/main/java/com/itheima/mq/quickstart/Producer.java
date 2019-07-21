package com.itheima.mq.quickstart;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @Description:消息生产
 * @author : xyq
 * @date: 2019/4/5/18:49
 */
public class Producer {

    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        //消息发送有这么几个步骤：
        //1.创建DefaultMQProducer
        DefaultMQProducer producer = new DefaultMQProducer("demo_producer_group");   //消息发送组
        //2.设置Namesrv地址
        producer.setNamesrvAddr("192.168.10.128:9876");
        //3.开启DefaultMQProducer
        producer.start();
        //4.创建消息Message String topic, String tags, String keys, byte[] body
        Message message = new Message("Topic_Demo", //主题
                                                                "Tags",             //主要用于消息过滤（需要什么特定类型的消息）
                                                                "Keys_1",            //消息的唯一值，用来定位当前消息信息
                                                                "hello!".getBytes(RemotingHelper.DEFAULT_CHARSET)
        );
        //5.发送消息
        SendResult sendResult = producer.send(message);
        System.out.println(sendResult);
        //6.关闭DefaultMQProducer
        producer.shutdown();
    }
}
