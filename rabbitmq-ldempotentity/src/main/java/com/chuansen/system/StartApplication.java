package com.chuansen.system;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

@SpringBootApplication
@MapperScan("com.chuansen.system.mapper")
public class StartApplication {

    public static void main(String... args) throws Exception {
        ConfigurableApplicationContext application = SpringApplication.run(StartApplication.class, args);

        Environment environment = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = environment.getProperty("server.port");
        String path = environment.getProperty("server.servlet.context-path");
        System.out.println("External: http://" + ip + ":" + port + path );
    }
}
