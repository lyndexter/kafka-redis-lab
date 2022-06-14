package com.iot.lab4interfaces.strategies;

import org.springframework.stereotype.Component;

@Component
public class SendTextToConsole implements SendText {
    @Override
    public void sendText(String text) {
        System.out.println(text);
    }
}
