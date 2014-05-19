package com.evrythng.demo.controller;

import com.evrythng.demo.model.VirtualObject;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created with IntelliJ IDEA.
 * User: patrice
 * Date: 17/05/2014
 * Mapping Websocket endpoints
 */
@Controller
public class Websocket {

    @MessageMapping("/image")
    @SendTo("/topic/confirm")
    public boolean receiveImageFromClient(String message) {
        System.out.println(message);
        boolean success = false;
        JSONObject jo;
        VirtualObject vo = new VirtualObject();

        try {
            jo = new JSONObject(message);

            if(message.contains("longitude") && message.contains("latitude")) {
                vo.setLongitude((Double) jo.get("longitude"));
                vo.setLatitude((Double) jo.get("latitude"));
            } else {
                vo.setLongitude(0.0);
                vo.setLatitude(0.0);
            }

            vo.setImgBase64((String) jo.get("imgBase64"));

            vo.expandDataFromQRCode((String) jo.get("imgBase64"));

            success = vo.serialize();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return success;
    }

}
