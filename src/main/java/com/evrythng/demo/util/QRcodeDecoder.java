package com.evrythng.demo.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: patrice
 * Date: 17/05/2014
 */
public class QRcodeDecoder {

    private static Map hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();;

    private QRcodeDecoder() {
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
    }

    public static String readQRCodeFromBase64(String imgBase64) throws IOException, NotFoundException {

        BinaryBitmap binaryBitmap = new BinaryBitmap(
                new HybridBinarizer(
                    new BufferedImageLuminanceSource(
                            getBufferedImageFromBase64(imgBase64)
                    )
                )
            );
        Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap,
                hintMap);
        return qrCodeResult.getText();
    }

    private static BufferedImage getBufferedImageFromBase64(String imgBase64) throws IOException {

        BASE64Decoder decoder = new BASE64Decoder();
        byte[] imageByte = decoder.decodeBuffer(imgBase64);
        ByteArrayInputStream bais = new ByteArrayInputStream(imageByte);

        return ImageIO.read(bais);
    }

}


