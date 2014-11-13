package aurora;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jssc.*;

public class DeviceCommunication implements SerialPortEventListener {

    static SerialPort serialPort;

    public void showScrollingTextMessage(ScrollingTextMessage message) throws JsonProcessingException {
        String portName = "COM1";

        String[] portNames = SerialPortList.getPortNames();
        for (int i = 0; i < portNames.length; i++) {
            System.out.println(portNames[i]);
            portName = portNames[i];
        }

        MessageWrapper wrapper = new MessageWrapper(message);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(wrapper);

        SerialPort serialPort = new SerialPort(portName);
        try {
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            serialPort.writeString(json);
            System.out.println(serialPort.readString());
            serialPort.closePort();
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }

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