package aurora;

import jssc.SerialPortException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@org.springframework.stereotype.Controller
public class PatternController {

    static String[] patterns;

    @RequestMapping(value = "/pattern", method = RequestMethod.GET)
    public String getPatterns(Model model) throws IOException, SerialPortException {
        if (patterns == null || patterns.length < 1) {
            ResultSet resultSet = new DeviceCommunication().listPatterns();
            patterns = resultSet.getResults();
        }

        model.addAttribute("pattern", new PatternWrapper("FlowField"));
        model.addAttribute("patterns", patterns);

        return "patternsList";
    }

    @RequestMapping(value = "/pattern", method = RequestMethod.POST)
    public String patternSubmit(@ModelAttribute PatternWrapper pattern, Model model) throws IOException, SerialPortException {
        try {
            new DeviceCommunication().showPattern(pattern);
            model.addAttribute("status", "Success!");
        } catch (Exception ex) {
            model.addAttribute("status", ex.getMessage());
        }

        if (patterns == null || patterns.length < 1) {
            ResultSet resultSet = new DeviceCommunication().listPatterns();
            patterns = resultSet.getResults();
        }

        model.addAttribute("pattern", pattern);
        model.addAttribute("patterns", patterns);

        return "patternsList";
    }
}
