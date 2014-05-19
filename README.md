##Experiment with Evrythng

This experiment is showing a possible architecture to manage virtual objects for augmented reality purposes.
In augmented reality, virtual objects are overlaying the real world, therefore a common virtual layer is needed,
holding information about virtual objects.
If Evrythng would host the virtual layer containing information about virtual objects,
third parties could develop better apps, focusing on the capture and rendering of virtual objects.
Applications could share this common virtual world, improving and maintaining it.

###About this project:

This project is using websockets and is receiving data captured by a [client device](https://github.com/Pat-rice/demo-capture).
The data sent to the server should be a QR code containing JSON data.
In this example, an android mobile phone is used and it can also collect GEOlocation data.
A simple STOMP protocol has been used over the web socket and this is not ideal to send images,
few glitches are present.
The server parse these data and push it to EvrythngAPI.

The QR code parsing is happening on the server to eventually give more flexibility to the devices capturing the data.
Otherwise a qr code scanner should be implemented on every devices.


###Ideas to explore:

    Streaming live data to server
    Handling data noise
    Display Virtual object in real time
    3D "world map" of virtual objects
    Notification when nearby a virtual object
    Replace STOMP protocol by binary
    3G/4G connections


###Run it
1.  Clone it
2.  Add your API KEY into com.evrythng.demo.util.EvrythngAPI
3.  mvn clean install
4.  Deploy the warfile in target/
