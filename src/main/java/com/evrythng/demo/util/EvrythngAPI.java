package com.evrythng.demo.util;

import com.evrythng.demo.model.VirtualObject;
import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.exception.EvrythngException;
import com.evrythng.java.wrapper.service.ThngService;
import com.evrythng.thng.resource.model.store.Location;
import com.evrythng.thng.resource.model.store.Thng;
import com.google.zxing.NotFoundException;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: patrice
 * Date: 18/05/2014
 */
public class EvrythngAPI {

    private static final String API_KEY = "YOUR_API_KEY";
    private static final ApiManager api = new ApiManager(API_KEY);
    private static final ThngService thngService = api.thngService();

    private EvrythngAPI() {

    }

    /**
     * Serialize virtual object by using Thng and Location and parsing JSON data from QR code
     * @param vo: VirtualObject
     * @return VirtualObject
     */
    public static VirtualObject serializeVirtualObject(VirtualObject vo) {

        //Create location object from VO
        Location l = new Location();
        l.setLatitude(vo.getLatitude());
        l.setLongitude(vo.getLongitude());

        //Transform VO into Thng
        Thng t = vo;
        t.addCustomFields("shape", vo.getShape());
        t.addCustomFields("imgBase64", vo.getImgBase64());


        t = serializeThng(vo, l);
        //Update VO with newly created Thng
        vo.setId(t.getId());
        vo.setCreatedAt(t.getCreatedAt());
        vo.setUpdatedAt(t.getUpdatedAt());

        return vo;

    }


    /**
     * Serialize an Evrythng Thng object to Evrythng DB
     * @param t: Thng
     * @param l: Location
     * @return boolean: success
     */
    private static Thng serializeThng(Thng t, Location l) {
        try {
            t = thngService.thngCreator(t).execute();
            thngService.locationUpdater(t.getId(), l).execute();
        } catch (EvrythngException e) {
            e.printStackTrace();
        }

        return t;
    }

}
