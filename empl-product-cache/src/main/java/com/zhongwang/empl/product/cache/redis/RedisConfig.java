package com.zhongwang.empl.product.cache.redis;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @Description: Redis配置类:redis连接工厂放弃JedisConnectionFactory使用LettuceConnectionFactory
 * @author: slliver
 * @date: 2019/9/4 13:12
 * @version: 1.0
 */
@Configuration
@EnableCaching
@PropertySource("classpath:properties/redis.properties")
public class RedisConfig {

    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Value(value = "${redis.master.database}")
    private Integer masterDatabase;
    @Value(value = "${redis.master.host}")
    private String masterHost;
    @Value(value = "${redis.master.port}")
    private Integer masterPort;
    @Value(value = "${redis.master.password}")
    private String masterPassword;

    @Value(value = "${redis.slave.database}")
    private Integer slaveDatabase;
    @Value(value = "${redis.slave.host}")
    private String slaveHost;
    @Value(value = "${redis.slave.port}")
    private Integer slavePort;
    @Value(value = "${redis.slave.password}")
    private String slavePassword;

    // 如使用注解的话需要配置cacheManager
    @Bean(name = "redisCacheManager")
    CacheManager cacheManager() {
        RedisConnectionFactory connectionFactory = getLeeruceConnectionFactory(masterDatabase, masterHost, masterPort, masterPassword);
        // 初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        // 设置默认超过期时间是1分钟
        redisCacheConfiguration.entryTtl(Duration.ofMinutes(1));
        // 初始化RedisCacheManager
        RedisCacheManager cacheManager = RedisCacheManager.builder(redisCacheWriter).cacheDefaults(redisCacheConfiguration).build();
        return cacheManager;
    }

    /**
     * 如果Bean的name为masterRedisTemplate这样的话RedisAutoConfiguration里的RedisTemplate还是会初始化
     * 如果Bean的name为redisTemplate这样的话RedisAutoConfiguration里的RedisTemplate不会初始化
     */
    @Bean(name = "masterRedisTemplate")
    public RedisTemplate<String, Object> masterRedisTemplate() {
        logger.info(this.getClass().getName() + " redisTemplate init === >> OK...");
        RedisConnectionFactory connectionFactory = getLeeruceConnectionFactory(masterDatabase, masterHost, masterPort, masterPassword);
        return getRedisTemplate(connectionFactory);
    }

    @Bean(name = "slaveRedisTemplate")
    public RedisTemplate<String, Object> slaveRedisTemplate() {
        RedisConnectionFactory connectionFactory = getLeeruceConnectionFactory(slaveDatabase, slaveHost, slavePort, slavePassword);
        return getRedisTemplate(connectionFactory);
    }

    /**
     * 键值对的序列化采用FastJsonRedisSerializer方式
     */
    public RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        RedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);
        // value的序列化方式采用fastjson
        template.setValueSerializer(new FastJsonRedisSerializer<>(Object.class));
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(new FastJsonRedisSerializer<>(Object.class));
        // 设置redis连接工厂
        template.setConnectionFactory(redisConnectionFactory);
        template.afterPropertiesSet();

        return template;
    }

    /**
     * 键值对的序列化采用Jackson2JsonRedisSerializer方式
     */
    public RedisTemplate<String, Object> getRedisTemplate2(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 使用Jackson2JsonRedisSerialize替换默认序列化redis的value值(默认使用JDK的序列化方式JdkSerializationRedisSerializer)
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        RedisSerializer redisKeySerializer = new StringRedisSerializer();
        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(redisKeySerializer);
        // value的序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(redisKeySerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        // 设置redis连接工厂
        template.setConnectionFactory(redisConnectionFactory);
        // 设置完调用一下
        template.afterPropertiesSet();

        return template;
    }

    /**
     * LettuceConnectionFactory 连接工厂
     */
    private LettuceConnectionFactory getLeeruceConnectionFactory(Integer database, String host, Integer port, String password) {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(host, port);
        configuration.setDatabase(database);
        RedisPassword redisPassword = RedisPassword.of(password);
        configuration.setPassword(redisPassword);
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(configuration);
        lettuceConnectionFactory.afterPropertiesSet();
        return lettuceConnectionFactory;
    }

    /**
     * JedisConnectionFactory 连接工厂
     */
    private JedisConnectionFactory getJedisConnectionFactory(Integer database, String host, Integer port, String password) {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(host, port);
        configuration.setDatabase(database);
        RedisPassword redisPassword = RedisPassword.of(password);
        configuration.setPassword(redisPassword);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(configuration);
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }
}
