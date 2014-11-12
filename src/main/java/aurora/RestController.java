package aurora;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private static final String template = "Hello, %s!";

    @RequestMapping("/message")
    public ScrollingTextMessage findAllMessages() {

        new DeviceCommunication().showScrollingTextMessage(new ScrollingTextMessage());

        return new ScrollingTextMessage(
                "This is a scrolling text message!",
                255,
                255,
                255,
                1,
                1,
                "gohufont11b",
                28,
                "wrapForward"
        );
    }
}
