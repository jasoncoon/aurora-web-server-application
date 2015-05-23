package aurora;

public class WeatherTypeWrapper {
  private int weatherType;

  public WeatherTypeWrapper() {
  }

  public WeatherTypeWrapper(int weatherType) {
    this.weatherType = weatherType;
  }

  public int getWeatherType() {
    return weatherType;
  }

  public void setWeatherType(int weatherType) {
    this.weatherType = weatherType;
  }
}
