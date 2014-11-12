package aurora;

import jssc.SerialPortException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class Application {
    public static void main(String[] args) throws SerialPortException {
        DeviceCommunication deviceCommunication = new DeviceCommunication();

//        deviceCommunication.initialize();

        SpringApplication.run(Application.class, args);

//        deviceCommunication.close();
    }
}
