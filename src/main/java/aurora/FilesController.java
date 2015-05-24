package aurora;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.stereotype.Controller
public class FilesController {

  @RequestMapping(value = "/files", method = RequestMethod.GET)
  public String file(Model model) {
    UploadFile file = new UploadFile();

    model.addAttribute("file", file);

    return "files";
  }

  @RequestMapping(value = "/files", method = RequestMethod.POST)
  public String fileSubmit(@ModelAttribute UploadFile file, Model model) {
    model.addAttribute("file", file);

    try {
      new DeviceCommunication().uploadFile(file);
      model.addAttribute("status", "Success!");
    }
    catch(Exception ex) {
      model.addAttribute("status", ex.getMessage());
    }

    return "files";
  }
}
