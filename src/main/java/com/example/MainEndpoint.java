package com.example;

import com.example.config.CorrelationIdRequestInterceptor;
import feign.Feign;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
public class MainEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainEndpoint.class);

    @GetMapping("/hello")
    public String hello() {
        FooClient fooClient = Feign.builder()
                .requestInterceptor(new CorrelationIdRequestInterceptor())
                .target(FooClient.class, "http://localhost:8080/foo");

        LOGGER.info("Acessado url /hello");
        String foo = fooClient.data();

        String msg = "Hello World!<br/>";
        msg += "Foo from /foo: <br/>";
        msg += foo;

        return msg;
    }

    @GetMapping("/foo")
    public String foo() {
        LOGGER.info("Acessado url /foo");

        return "Foo data!";
    }

}
