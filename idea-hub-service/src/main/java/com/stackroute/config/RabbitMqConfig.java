package com.stackroute.config;

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
public class RabbitMqConfig {

    @Value("${javainuse.idea.queue}")
    String ideaQueue;

    @Value("${javainuse.idea.exchange}")
    String ideaExchange;

    @Value("${javainuse.idea.routingkey}")
    String ideaRoutingkey;

    @Bean
    Queue ideaQueue() {
        return new Queue(ideaQueue,true);

    }

    @Bean
    Exchange ideaExchange(){
        return ExchangeBuilder.topicExchange(ideaExchange).durable(true).build();
    }
    @Bean
    Binding innovatorBinding(){

        // Binding
        return BindingBuilder.bind(ideaQueue()).to(ideaExchange()).with(ideaRoutingkey).noargs();
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

