package com.microservice.skeleton.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @Author: pjx
 * @Date: 2019/1/14 11:19
 * @Version 1.0
 */
@Configuration
public class RabbitConfig {

    public final static String QUEUE_NAME = "rabbit_queue";
    public final static String EXCHANGE_NAME = "rabbit_exchange";
    public final static String ROUTING_KEY = "rabbit_key";

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private String port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.publisher-confirms}")
    private  Boolean  publisherConfirms;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    @Bean
    public Queue queue(){
        return new Queue(QUEUE_NAME);
    }

    // 创建一个 topic 类型的交换器
    @Bean
    public TopicExchange topicExchange(){
        return  new TopicExchange(EXCHANGE_NAME);
    }

    // 使用路由键（routingKey）把队列（Queue）绑定到交换器（Exchange）
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
    //创建工厂连接
    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(this.host);
        // connectionFactory.setPort(this.port);
        connectionFactory.setUsername(this.username);
        connectionFactory.setPassword(this.password);
        connectionFactory.setVirtualHost(this.virtualHost);
        connectionFactory.setPublisherConfirms(this.publisherConfirms); //必须要设置
        return  connectionFactory;
    }
    //rabbitmq的模板配置
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)//必须是prototype
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(this.connectionFactory());
        return  rabbitTemplate;
    }
}
