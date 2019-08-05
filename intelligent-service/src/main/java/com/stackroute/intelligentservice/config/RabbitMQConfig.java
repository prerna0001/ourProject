package com.stackroute.intelligentservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
    @Value("${intelligent.queue}")    //taking intelligent.queue name from application.properties file
    String intelligentQueue;

    @Value("${serviceProvider.exchange}")    //taking serviceProvider.exchange name from application.properties file
    String serviceProviderExchange;

    @Value("${serviceProvider.routingkey}")    //taking serviceProvider.routingkey name from application.properties file
    private String serviceProviderRoutingKey;


    @Bean
    Queue intelligentQueue()
    {

        return new Queue(intelligentQueue,true);
    }
    @Bean
    Exchange serviceProviderExchange(){
        return ExchangeBuilder.topicExchange(serviceProviderExchange).durable(true).build();   //to enable exchange
    }
    @Bean
    Binding serviceProviderBinding(){     //method to bind intelligent queue to service provider exchange with serviceProvider exchange key
        return BindingBuilder
                .bind(intelligentQueue())
                .to(serviceProviderExchange())
                .with(serviceProviderRoutingKey)
                .noargs();
    }


    @Bean
    ConnectionFactory connectionFactory(){    //we want connection to be stable,so that we needn't close or open connection

        CachingConnectionFactory cachingConnectionFactory =new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername("guest");      //username to login to rabbitmq
        cachingConnectionFactory.setPassword("guest");      //password to login to rabbitmq
        return cachingConnectionFactory;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {  //for decrypting the message recived by producer
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {     //to recive message from consumer
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

}
