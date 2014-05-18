package com.evrythng.demo.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: patrice
 * Date: 17/05/2014
 */
@Controller
public class Websocket {


    @MessageMapping("/image")
//    @SendTo("/topic/prout")
    public String receiveImageFromClient(String message) {


        return message;
    }

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public org.springframework.http.ResponseEntity<String> getPassesEventsByMatch() {

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> response = new ResponseEntity<String>("hey !", responseHeaders, HttpStatus.OK);
        return response;
    }
}
