package org.edu.config;


import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;

@Configuration
public class PubSubConfig {

    @Bean
    @ServiceActivator(inputChannel = "myOutputChannel")
    public MessageHandler messageSender(PubSubTemplate pubsubTemplate) {
        return new PubSubMessageHandler(pubsubTemplate, "edu_sub");
    }

    @MessagingGateway(defaultRequestChannel = "myOutputChannel")
    public interface GCPEventSender {
        void sendToPubsub(String event);
    }
}
