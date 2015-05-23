package aurora;

import java.io.IOException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jssc.SerialPortException;

@org.springframework.stereotype.Controller
public class WeatherController {

  @RequestMapping(value = "/weather", method = RequestMethod.GET)
  public String getWeather(Model model) throws IOException, SerialPortException {
    model.addAttribute("weather", new WeatherWrapper(100, 0));

    return "weather";
  }

  @RequestMapping(value = "/weather", method = RequestMethod.POST)
  public String weatherSubmit(@ModelAttribute WeatherWrapper weather, Model model) throws IOException, SerialPortException {
    try {
      DeviceCommunication deviceCommunication = new DeviceCommunication();
      deviceCommunication.setWeatherType(new WeatherTypeWrapper(weather.getWeatherType()));
      deviceCommunication.setTemperature(new TemperatureWrapper(weather.getTemperature()));
      model.addAttribute("status", "Success!");
    } catch (Exception ex) {
      model.addAttribute("status", ex.getMessage());
    }

    model.addAttribute("weather", weather);

    return "weather";
  }
}
