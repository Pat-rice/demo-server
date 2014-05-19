package com.evrythng.demo.model;

import com.evrythng.demo.util.EvrythngAPI;
import com.evrythng.demo.util.QRcodeDecoder;
import com.evrythng.thng.resource.model.store.Thng;
import com.google.zxing.NotFoundException;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: patrice
 * Date: 18/05/2014
 *
 * Representation of VirtualObject
 */
public class VirtualObject extends Thng{

    String shape;
    String imgBase64;
    double longitude;
    double latitude;

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getImgBase64() {
        return imgBase64;
    }

    public void setImgBase64(String imgBase64) {
        this.imgBase64 = imgBase64;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void expandDataFromQRCode(String imgBase64) {

        //Read and parse data from Image Base64 QRcode
        try {

            String jsonString = QRcodeDecoder.readQRCodeFromBase64(imgBase64);
            JSONObject jo = new JSONObject(jsonString);
            this.setName((String) jo.get("name"));
            this.setDescription((String) jo.get("description"));
            this.setShape((String) jo.get("shape"));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public boolean serialize() {

        VirtualObject temp = EvrythngAPI.serializeVirtualObject(this);
        if (temp.getId() != null) {
            return true;
        } else {
            return false;
        }

    }

}
