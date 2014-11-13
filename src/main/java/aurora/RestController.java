package aurora;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @RequestMapping(value = "api/message", method = RequestMethod.POST)
    public void showScrollingTextMessage(@RequestParam(value="message") ScrollingTextMessage message) throws JsonProcessingException {
        new DeviceCommunication().showScrollingTextMessage(message);
    }
}
