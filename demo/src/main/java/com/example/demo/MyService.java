package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class MyService {
    public String getMessage() {
        return "This is a service message!";
    }
}