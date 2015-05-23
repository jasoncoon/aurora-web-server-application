package aurora;

import java.io.IOException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jssc.SerialPortException;

@org.springframework.stereotype.Controller
public class AudioPatternController {

    static String[] patterns;

    @RequestMapping(value = "/audiopattern", method = RequestMethod.GET)
    public String getPatterns(Model model) throws IOException, SerialPortException {
        if (patterns == null || patterns.length < 1) {
            ResultSet resultSet = new DeviceCommunication().listAudioPatterns();
            patterns = resultSet.getResults();
        }

        model.addAttribute("audioPattern", new AudioPatternWrapper("0"));
        model.addAttribute("audioPatterns", patterns);

        return "audioPatternsList";
    }

    @RequestMapping(value = "/audiopattern", method = RequestMethod.POST)
    public String patternSubmit(@ModelAttribute AudioPatternWrapper pattern, Model model) throws IOException, SerialPortException {
        try {
            new DeviceCommunication().showAudioPattern(pattern);
            model.addAttribute("status", "Success!");
        } catch (Exception ex) {
            model.addAttribute("status", ex.getMessage());
        }

        if (patterns == null || patterns.length < 1) {
            ResultSet resultSet = new DeviceCommunication().listPatterns();
            patterns = resultSet.getResults();
        }

        model.addAttribute("audioPattern", pattern);
        model.addAttribute("audioPatterns", patterns);

        return "audioPatternsList";
    }
}
