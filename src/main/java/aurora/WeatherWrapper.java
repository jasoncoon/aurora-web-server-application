package aurora;

public class WeatherWrapper {
  private int temperature;
  private int weatherType;

  public WeatherWrapper() {
  }

  public WeatherWrapper(int temperature) {
    this.temperature = temperature;
  }

  public WeatherWrapper(int temperature, int weatherType) {
    this.temperature = temperature;
    this.weatherType = weatherType;
  }

  public int getTemperature() {
    return temperature;
  }

  public void setTemperature(int temperature) { this.temperature = temperature; }

  public int getWeatherType() {
    return weatherType;
  }

  public void setWeatherType(int weatherType) {
    this.weatherType = weatherType;
  }
}
