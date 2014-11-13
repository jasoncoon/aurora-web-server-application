package aurora;

import com.fasterxml.jackson.core.JsonProcessingException;
import jssc.SerialPortException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.stereotype.Controller
public class PatternController {

    static final String[] patterns = new String[]{
            "FlowField",
            "Spin",
            "Noise",
            "Wave",
            "Attract",
            "Bounce",
            "Flock",
            "Infinity",
            "Plasma",
            "Invaders",
            "Snake",
            "Cube",
            "Fire",
            "Life",
            "Maze",
            "Pulse",
            "RainbowSmoke",
            "Spark",
            "Spiral",
            "Tetrahedron",
            "Ghost",
            "Dots1",
            "Dots2",
            "SlowMandala",
            "Mandala8"
    };

    @RequestMapping(value = "/pattern", method = RequestMethod.GET)
    public String getPatterns(Model model) {

        model.addAttribute("pattern", new PatternWrapper("FlowField"));
        model.addAttribute("patterns", patterns);

        return "patternsList";
    }

    @RequestMapping(value = "/pattern", method = RequestMethod.POST)
    public String patternSubmit(@ModelAttribute PatternWrapper pattern, Model model) {
      model.addAttribute("pattern", pattern);
      model.addAttribute("patterns", patterns);

      try {
        new DeviceCommunication().showPattern(pattern);
        model.addAttribute("status", "Success!");
      }
      catch(Exception ex) {
        model.addAttribute("status", ex.getMessage());
      }

      return "patternsList";
    }
}
