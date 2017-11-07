package com.example;

import feign.RequestLine;

public interface FooClient {

    @RequestLine("GET")
    String data();
}
