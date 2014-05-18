package com.evrythng.demo.util;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import sun.misc.BASE64Decoder;

/**
 * Created with IntelliJ IDEA.
 * User: patrice
 * Date: 17/05/2014
 */
public class QRcodeDecoder {

    public static void main(String[] args) throws WriterException, IOException,
            NotFoundException {
        String qrCodeData = "Hello World!";
        String filePath = "QRCode.png";
        String charset = "UTF-8"; // or "ISO-8859-1"
        Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

//        createQRCode(qrCodeData, filePath, charset, hintMap, 200, 200);
//        System.out.println("QR Code image created successfully!");

//        System.out.println("Data read from QR Code: "
//                + readQRCode(filePath, charset, hintMap));
        String imgBase64 = "iVBORw0KGgoAAAANSUhEUgAAAMgAAADIAQAAAACFI5MzAAAA3UlEQVR42u3XTQ6EIAwF4LLqMbipwE09Bis7bQEzP8yaZwJxgX6bF6QVSf4N2rJlyzOkko4gZ6ox2zQiiU1LjRdLGbc4kikWTW1OAVBaakwRcYcTGWs53wcrxauk2qJO62eljJHbs0nfWSeW2vKmSgefB8PJxdr5+tIiSatdQ+3N9J56ubTKqN78tFYESfQl60qy5j3DdwWvlhG82DP7bgBJ34BaJd6bGUq8K+seJKvgj7cNIO1LaxOWiyKeUPLg6Tc1gIT7cIck7RSQ/ApY0quExvkOSPb/3JYtD5UXIypzJPmYRYgAAAAASUVORK5CYII=";
        String result = readQRCodeFromBase64(imgBase64, charset, hintMap);
        System.out.println(result);
    }

/*    public static void createQRCode(String qrCodeData, String filePath,
                                    String charset, Map hintMap, int qrCodeheight, int qrCodewidth)
            throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(
                new String(qrCodeData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);

        MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                .lastIndexOf('.') + 1), new File(filePath));
    }

    public static String readQRCode(String filePath, String charset, Map hintMap)
            throws FileNotFoundException, IOException, NotFoundException {
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
                new BufferedImageLuminanceSource(
                        ImageIO.read(new FileInputStream(filePath)))));
        Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap,
                hintMap);
        return qrCodeResult.getText();
    }*/

    public static String readQRCodeFromBase64(String imgBase64, String charset, Map hintMap) throws IOException, NotFoundException {

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


