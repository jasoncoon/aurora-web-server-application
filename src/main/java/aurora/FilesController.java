package aurora;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@org.springframework.stereotype.Controller
public class FilesController {

  @RequestMapping(value = "/files", method = RequestMethod.GET)
  public String file(Model model) {
    return "files";
  }

  @RequestMapping(value = "/files", method = RequestMethod.POST)
  public String fileSubmit(@RequestParam("name") String name, @RequestParam("file") MultipartFile file, Model model) {
    try {
      new DeviceCommunication().uploadFile(name, file);
      model.addAttribute("status", "Success!");
    }
    catch(Exception ex) {
      model.addAttribute("status", ex.getMessage());
    }

    return "files";
  }
}
