package com.cmazxiaoma.elasticsearch;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/10/24 14:40
 */
@Configuration
public class ElasticSearchConfig {

    @Bean
    public TransportClient client() throws UnknownHostException {

        Settings settings = Settings.builder()
                .put("cluster.name", "mayday")
                .build();
        PreBuiltTransportClient client = new PreBuiltTransportClient(settings);
        int[] localPorts = {9300, 9301, 9302};
        for (int port : localPorts) {
            client.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.10.6"), port));
        }
        return client;
    }
}
