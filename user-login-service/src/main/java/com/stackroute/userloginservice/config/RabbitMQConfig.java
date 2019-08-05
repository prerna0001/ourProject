package com.stackroute.userloginservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RabbitMQConfig {
    @Value("${serviceProvider.queue}")
    String serviceProviderQueueName;

    @Value("${serviceProvider.exchange}")
    String serviceProviderExchange;

    @Value("${serviceProvider.routingkey}")
    private String serviceProviderRoutingKey;


    @Value("${innovator.queue}")
    String innovatorQueueName;

    @Value("${innovator.exchange}")
    String innovatorExchange;

    @Value("${innovator.routingkey}")
    private String innovatorRoutingKey;


    @Bean
    Queue servicePQueue()
    {
        return new Queue(serviceProviderQueueName,true);
    }
    @Bean
    Exchange servicePExchange(){
        return ExchangeBuilder.topicExchange(serviceProviderExchange).durable(true).build();
    }
    @Bean
    Binding servicePBinding(){
        //this is the traditional way of binding
//        return new Binding(MY_QUEUE, Binding.DestinationType.QUEUE,"myTopicExchange","topic",null);
        //more declarative way of binding
        return BindingBuilder
                .bind(servicePQueue())
                .to(servicePExchange())
                .with(serviceProviderRoutingKey)
                .noargs();
    }

    @Bean
    Queue innovatorQueue()
    {
        return new Queue(innovatorQueueName,true);
    }
    @Bean
    Exchange innovatorExchange(){
        return ExchangeBuilder.topicExchange(innovatorExchange).durable(true).build();
    }
    @Bean
    Binding innovatorBinding(){
        //this is the traditional way of binding
//        return new Binding(MY_QUEUE, Binding.DestinationType.QUEUE,"myTopicExchange","topic",null);
        //more declarative way of binding
        return BindingBuilder
                .bind(innovatorQueue())
                .to(innovatorExchange())
                .with(innovatorRoutingKey)
                .noargs();
    }


    @Bean
    ConnectionFactory connectionFactory(){
        //we want connection to be stable,so that we needn't close or open connection
        CachingConnectionFactory cachingConnectionFactory =new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest");
        return cachingConnectionFactory;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
