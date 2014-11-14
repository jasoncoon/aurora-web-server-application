package aurora;

import jssc.SerialPortException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@org.springframework.stereotype.Controller
public class AnimationController {

    static String[] animations;

    @RequestMapping(value = "/animation", method = RequestMethod.GET)
    public String getAnimations(Model model) throws IOException, SerialPortException {
        if (animations == null || animations.length < 1) {
            ResultSet resultSet = new DeviceCommunication().listAnimations();
            animations = resultSet.getResults();
        }

        model.addAttribute("animation", new AnimationWrapper("FlowField"));
        model.addAttribute("animations", animations);

        return "animationsList";
    }

    @RequestMapping(value = "/animation", method = RequestMethod.POST)
    public String animationSubmit(@ModelAttribute AnimationWrapper animation, Model model) throws IOException, SerialPortException {
        try {
            new DeviceCommunication().showAnimation(animation);
            model.addAttribute("status", "Success!");
        } catch (Exception ex) {
            model.addAttribute("status", ex.getMessage());
        }

        if (animations == null || animations.length < 1) {
            ResultSet resultSet = new DeviceCommunication().listAnimations();
            animations = resultSet.getResults();
        }

        model.addAttribute("animation", animation);
        model.addAttribute("animations", animations);
        
        return "animationsList";
    }
}
