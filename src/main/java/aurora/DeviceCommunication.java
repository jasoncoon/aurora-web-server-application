package aurora;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class DeviceCommunication implements SerialPortEventListener {

  static SerialPort serialPort;

  public ResultSet listPalettes() throws IOException, SerialPortException {
    ObjectMapper mapper = new ObjectMapper();
    CommandWrapper wrapper = new CommandWrapper("ListPalettes");
    String json = mapper.writeValueAsString(wrapper);
    String results = sendJson(json, true);
    return mapper.readValue(results, ResultSet.class);
  }

  public void showPalette(PaletteWrapper wrapper) throws JsonProcessingException, SerialPortException {
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(wrapper);

    sendJson(json, false);
  }

  public ResultSet listAudioPatterns() throws IOException, SerialPortException {
    ObjectMapper mapper = new ObjectMapper();
    CommandWrapper wrapper = new CommandWrapper("ListAudioPatterns");
    String json = mapper.writeValueAsString(wrapper);
    String results = sendJson(json, true);
    return mapper.readValue(results, ResultSet.class);
  }

  public void showAudioPattern(AudioPatternWrapper wrapper) throws JsonProcessingException, SerialPortException {
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(wrapper);

    sendJson(json, false);
  }

  public ResultSet listPatterns() throws IOException, SerialPortException {
    ObjectMapper mapper = new ObjectMapper();
    CommandWrapper wrapper = new CommandWrapper("ListPatterns");
    String json = mapper.writeValueAsString(wrapper);
    String results = sendJson(json, true);
    return mapper.readValue(results, ResultSet.class);
  }

  public void showPattern(PatternWrapper wrapper) throws JsonProcessingException, SerialPortException {
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(wrapper);

    sendJson(json, false);
  }

  public ResultSet listAnimations() throws IOException, SerialPortException {
    ObjectMapper mapper = new ObjectMapper();
    CommandWrapper wrapper = new CommandWrapper("ListAnimations");
    String json = mapper.writeValueAsString(wrapper);
    String results = sendJson(json, true);
    return mapper.readValue(results, ResultSet.class);
  }

  public void showAnimation(AnimationWrapper wrapper) throws JsonProcessingException, SerialPortException {
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(wrapper);

    sendJson(json, false);
  }

  public void setTemperature(TemperatureWrapper wrapper) throws JsonProcessingException, SerialPortException {
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(wrapper);

    sendJson(json, false);
  }

  public void setWeatherType(WeatherTypeWrapper wrapper) throws JsonProcessingException, SerialPortException {
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(wrapper);

    sendJson(json, false);
  }

  public void showScrollingTextMessage(ScrollingTextMessage message) throws JsonProcessingException, SerialPortException {
    MessageWrapper wrapper = new MessageWrapper(message);

    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(wrapper);

    sendJson(json, false);
  }

  private String sendJson(String json, boolean getResponse) throws SerialPortException {
    String portName = "COM1";

    String[] portNames = SerialPortList.getPortNames();
    for (int i = 0; i < portNames.length; i++) {
      System.out.println(portNames[i]);
      portName = portNames[i];
    }

    SerialPort serialPort = new SerialPort(portName);

    serialPort.openPort();
    serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
    serialPort.writeString(json);
    String buffer = null;
    StringBuffer results = new StringBuffer();
    if (getResponse) {
      Instant timeout = Instant.now().plusSeconds(5);
      while (serialPort.getInputBufferBytesCount() < 1) {
        if (Instant.now().isAfter(timeout))
          break;
      }
      while (serialPort.getInputBufferBytesCount() > 0 || Instant.now().isBefore(timeout)) {
        buffer = serialPort.readString();
        if (buffer != null)
          results.append(buffer);
      }
    }
    serialPort.closePort();
    return results.toString();
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

  public void uploadAnimation(String name, MultipartFile file) throws IOException, SerialPortException {
    String path = String.format("/gifs/%s.gif", name);
    path = path.toUpperCase();
    CreateFile createFile = new CreateFile();
    createFile.setPath(path);
    createFile.setLength(file.getSize());

    CreateFileWrapper wrapper = new CreateFileWrapper();
    wrapper.setCreateFile(createFile);

    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(wrapper);

    String portName = "COM1";

    String[] portNames = SerialPortList.getPortNames();
    for (int i = 0; i < portNames.length; i++) {
      System.out.println(portNames[i]);
      portName = portNames[i];
    }

    SerialPort serialPort = new SerialPort(portName);

    serialPort.openPort();
    serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
    serialPort.writeString(json);
    serialPort.closePort();

    serialPort = new SerialPort(portName);

    serialPort.openPort();
    serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

    InputStream stream = file.getInputStream();
    int b = stream.read();
    while (b > -1) {
      serialPort.writeByte((byte) b);
      b = stream.read();
    }
    stream.close();

    serialPort.closePort();
  }
}
