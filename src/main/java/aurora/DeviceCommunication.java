package aurora;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jssc.*;

public class DeviceCommunication implements SerialPortEventListener {

    static SerialPort serialPort;

    public void showPattern(PatternWrapper wrapper) throws JsonProcessingException, SerialPortException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(wrapper);

        sendJson(json);
    }

    public void showScrollingTextMessage(ScrollingTextMessage message) throws JsonProcessingException, SerialPortException {
        MessageWrapper wrapper = new MessageWrapper(message);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(wrapper);

        sendJson(json);
    }

    private void sendJson(String json) throws SerialPortException {
        String portName = "COM1";

        String[] portNames = SerialPortList.getPortNames();
        for (int i = 0; i < portNames.length; i++) {
            System.out.println(portNames[i]);
            portName = portNames[i];
        }

        SerialPort serialPort = new SerialPort(portName);

        serialPort.openPort();
        serialPort.setParams(SerialPort.BAUDRATE_9600,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);
        serialPort.writeString(json);
        System.out.println(serialPort.readString());
        serialPort.closePort();
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
