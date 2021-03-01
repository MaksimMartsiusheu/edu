package org.edu.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.edu.model.CourseEvent;
import org.edu.model.EventStatus;
import org.edu.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.AckMode;
import org.springframework.cloud.gcp.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.cloud.gcp.pubsub.support.BasicAcknowledgeablePubsubMessage;
import org.springframework.cloud.gcp.pubsub.support.GcpPubSubHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
@RequiredArgsConstructor
public class ConsumerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerConfig.class);

    private final ObjectMapper objectMapper;
    private final EventRepository eventRepository;

    @Bean
    public PubSubInboundChannelAdapter messageChannelAdapter(
            @Qualifier("pubsubInputChannel") MessageChannel inputChannel,
            PubSubTemplate pubSubTemplate) {
        PubSubInboundChannelAdapter adapter =
                new PubSubInboundChannelAdapter(pubSubTemplate, "edu_sub_callback");
        adapter.setOutputChannel(inputChannel);
        adapter.setPayloadType(String.class);
        adapter.setAckMode(AckMode.MANUAL);

        return adapter;
    }

    @Bean
    public MessageChannel pubsubInputChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "pubsubInputChannel")
    public MessageHandler messageReceiver() {
        return message -> {
            try {
                final CourseEvent event = objectMapper.readValue(message.getPayload().toString(), CourseEvent.class);
                BasicAcknowledgeablePubsubMessage originalMessage =
                        message.getHeaders().get(GcpPubSubHeaders.ORIGINAL_MESSAGE,
                                BasicAcknowledgeablePubsubMessage.class);
                originalMessage.ack();

                eventRepository.save(event);


            } catch (JsonProcessingException e) {
                LOGGER.error("Exception during processing", e);
            }
        };
    }
}
