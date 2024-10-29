package com.baloise.springtutorialbackend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Configuration
public class ProxyRestTemplateConfig {

    @Value("${rest.proxy.url}")
    private String proxyUrl;

    @Bean
    public RestTemplate createRestTemplate() {
        if (this.proxyUrl.isBlank()) {
            return new RestTemplate();
        } else {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, getProxyAddress());
            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            requestFactory.setProxy(proxy);

            return new RestTemplate(requestFactory);
        }
    }

    private InetSocketAddress getProxyAddress() {
        String[] urlParts = this.proxyUrl.split("://", 2)[1].split(":");
        return new InetSocketAddress(urlParts[0], Integer.parseInt(urlParts[1]));
    }
}
