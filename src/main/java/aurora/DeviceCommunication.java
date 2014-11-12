package aurora;

import jssc.*;

public class DeviceCommunication implements SerialPortEventListener {

    public void showScrollingTextMessage(ScrollingTextMessage message) {
        String[] portNames = SerialPortList.getPortNames();
        for(int i = 0; i < portNames.length; i++){
            System.out.println(portNames[i]);
        }

        SerialPort serialPort = new SerialPort("COM10");
        try {
            serialPort.openPort();//Open serial port
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);//Set params. Also you can set params by this string: serialPort.setParams(9600, 8, 1, 0);
            serialPort.writeString("{\"message\":{\"text\":\"Serial API is alive!\",\"red\":255,\"green\":255,\"blue\":255,\"top\":20,\"left\":4,\"font\":\"gohufont11b\",\"speed\":28,\"mode\":\"bounceReverse\"}}");
            // serialPort.writeString("{\"command\":\"Down\"}");
            System.out.println(serialPort.readString());
            serialPort.closePort();//Close serial port
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }

    static SerialPort serialPort;

//    public void initialize() throws SerialPortException {
//        String[] portNames = SerialPortList.getPortNames();
//        for(int i = 0; i < portNames.length; i++){
//            System.out.println(portNames[i]);
//        }
//
//        serialPort = new SerialPort("COM10");
//        serialPort.openPort();//Open port
//        serialPort.setParams(9600, 8, 1, 0);//Set params
//        int mask = SerialPort.MASK_RXCHAR;//Prepare mask
//        serialPort.setEventsMask(mask);//Set mask
//        serialPort.addEventListener(this);//Add SerialPortEventListener
//    }
//
//    public void close() throws SerialPortException {
//        serialPort.removeEventListener();
//        serialPort.closePort();
//    }

    public void serialEvent(SerialPortEvent event) {
        if (event.isRXCHAR()) {//If data is available
            try {
                System.out.println(serialPort.readString());
            } catch (SerialPortException ex) {
                System.err.println(ex);
            }
        }
    }
}