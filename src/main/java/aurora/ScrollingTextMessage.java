package aurora;

/*
{
  "text":"Hello World!",
  "red":255,
  "green":0,
  "blue":0,
  "top":15,
  "left":10,
  "font":"font8x13",
  "speed":80,
  "mode":"wrapForwardFromLeft"
}
 */
public class ScrollingTextMessage {
  private String text;
  private int red;
  private int green;
  private int blue;
  private int top;
  private int left;
  private String font;
  private int speed;
  private String mode;

  public ScrollingTextMessage(String text, int red, int green, int blue, int top, int left, String font, int speed, String mode) {
    this.text = text;
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.top = top;
    this.left = left;
    this.font = font;
    this.speed = speed;
    this.mode = mode;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public int getRed() {
    return red;
  }

  public void setRed(int red) {
    this.red = red;
  }

  public int getGreen() {
    return green;
  }

  public void setGreen(int green) {
    this.green = green;
  }

  public int getBlue() {
    return blue;
  }

  public void setBlue(int blue) {
    this.blue = blue;
  }

  public int getTop() {
    return top;
  }

  public void setTop(int top) {
    this.top = top;
  }

  public int getLeft() {
    return left;
  }

  public void setLeft(int left) {
    this.left = left;
  }

  public String getFont() {
    return font;
  }

  public void setFont(String font) {
    this.font = font;
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public String getMode() {
    return mode;
  }

  public void setMode(String mode) {
    this.mode = mode;
  }
}
