package com.stackroute.recommendationservice.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${serviceNeo4j.queue}")
    String serviceNeo4j;

    @Value("${serviceProvider.exchange}")
    String servicePExchange;

    @Value("${serviceProvider.routingkey}")
    String servicePRoutingkey;

    @Value("${javainuse.idea.queue}")
    String ideaQueue;

    @Value("${javainuse.idea.exchange}")
    String ideaexchange;

    @Value("${javainuse.idea.routingkey}")
    String idearoutingkey;

    @Bean
    Queue serviceNeo4jQueue() { return new Queue(serviceNeo4j,true);
                  }
    @Bean
    Exchange servicePExchange(){
        return ExchangeBuilder.topicExchange(servicePExchange).durable(true).build();
    }
    @Bean
    Binding innovatorBinding(){
        //this is the traditional way of binding
        return BindingBuilder.bind(serviceNeo4jQueue()).to(servicePExchange()).with(servicePRoutingkey).noargs();
    }
    @Bean
    Exchange ideaexchange(){
        return ExchangeBuilder.topicExchange(ideaexchange).durable(true).build();
    }

    @Bean
    Binding ideaBinding(){
        return BindingBuilder.bind(ideaQueue()).to(ideaexchange()).with(idearoutingkey).noargs();
    }

    @Bean
    Queue ideaQueue(){
        return  new Queue(ideaQueue,true);
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