package aurora;

public class TemperatureWrapper {
  private int temperature;

  public TemperatureWrapper() {
  }

  public TemperatureWrapper(int temperature) {
    this.temperature = temperature;
  }

  public int getTemperature() {
    return temperature;
  }

  public void setTemperature(int temperature) { this.temperature = temperature; }
}
