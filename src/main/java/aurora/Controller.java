package aurora;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller
public class Controller {

    static final String[] modes = new String[]{"wrapForward", "bounceForward", "bounceReverse", "stopped", "off", "wrapForwardFromLeft"};
    static final String[] fonts = new String[]{"font3x5", "font5x7", "font6x10", "font8x13", "gohufont11", "gohufont11b"};

    @RequestMapping("/")
    public String index(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public String messageForm(Model model) {
        ScrollingTextMessage message = new ScrollingTextMessage(
                "This is a message!",
                255,
                255,
                255,
                1,
                1,
                "gohufont11b",
                28,
                "wrapForward"
        );

        model.addAttribute("message", message);
        model.addAttribute("modes", modes);
        model.addAttribute("fonts", fonts);

        return "editMessage";
    }

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public String messageSubmit(@ModelAttribute ScrollingTextMessage message, Model model) throws JsonProcessingException {
        new DeviceCommunication().showScrollingTextMessage(message);
        model.addAttribute("message", message);
        model.addAttribute("modes", modes);
        model.addAttribute("fonts", fonts);
        model.addAttribute("status", "Success!");
        return "editMessage";
    }
}
