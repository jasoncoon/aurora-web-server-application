# Aurora Web Server Application
Web server application for controlling a [SmartMatrix](http://kck.st/1RUlTfb) display running [Aurora](https://github.com/pixelmatix/aurora) via the USB port.

[![SmartMatrix](https://ksr-ugc.imgix.net/projects/979600/photo-original.jpg?v=1432073505&w=640&h=480&fit=crop&auto=format&q=92&s=e7f872a3cb2df45c5fbad0a251e4025c)](http://pixl.mx/kick2015)

SmartMatrix is a self-contained 32x32 pixel LED matrix display for music visualization, dynamic patterns, pixel art, and more!

Features:

* Show custom scrolling or static messages.  Set the message text, font, color, position, scroll speed and scroll mode.
* Change to a specific pattern, chosen from a list of pattern names.
* Change to a specific animation, chosen from a list of animation names.
* Change to a specific palette, chosen from a list of palette names.

Built using [Spring Boot](http://projects.spring.io/spring-boot/) and Java.

To use:

1. Download source.
2. Build using Maven: mvn package
3. Run the built jar file: double click or run java -jar aurora-spring-boot-1.0-SNAPSHOT.jar
