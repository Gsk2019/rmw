package org.cs.redis;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/*redis信息的订阅这
 * 和RedisCtl.java订阅者组成发布/订阅
 * 希望和NotityCtl.java一起实现分布式消息发送
 * 缺陷是redis的发布和订阅是没有复发的，想读于MQ来说较弱，根据MQ来进行改进可以*/

public class RedisMessageListener implements MessageListener {

    private RedisTemplate redisTemplate;

    RedisSerializer serializer;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        serializer = redisTemplate.getValueSerializer();
        Object messageStr = serializer.deserialize(message.getBody());
        //Object channelmsg = serializer.deserialize(message.getChannel());
        String channel = new String(message.getChannel());
        byte[] channel1 = message.getChannel();

    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}