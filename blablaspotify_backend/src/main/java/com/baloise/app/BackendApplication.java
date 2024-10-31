package com.baloise.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.baloise")
@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SslUtils.disableSSLCertificateChecking();
        SpringApplication.run(BackendApplication.class, args);
    }

}
