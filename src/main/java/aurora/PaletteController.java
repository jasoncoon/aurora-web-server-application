package aurora;

import jssc.SerialPortException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@org.springframework.stereotype.Controller
public class PaletteController {

    static String[] palettes;

    @RequestMapping(value = "/palette", method = RequestMethod.GET)
    public String getPalettes(Model model) throws IOException, SerialPortException {
        if (palettes == null || palettes.length < 1) {
            ResultSet resultSet = new DeviceCommunication().listPalettes();
            palettes = resultSet.getResults();
        }

        model.addAttribute("palette", new PaletteWrapper("Rainbow"));
        model.addAttribute("palettes", palettes);

        return "palettesList";
    }

    @RequestMapping(value = "/palette", method = RequestMethod.POST)
    public String paletteSubmit(@ModelAttribute PaletteWrapper palette, Model model) throws IOException, SerialPortException {
        try {
            new DeviceCommunication().showPalette(palette);
            model.addAttribute("status", "Success!");
        } catch (Exception ex) {
            model.addAttribute("status", ex.getMessage());
        }

        if (palettes == null || palettes.length < 1) {
            ResultSet resultSet = new DeviceCommunication().listPalettes();
            palettes = resultSet.getResults();
        }

        model.addAttribute("palette", palette);
        model.addAttribute("palettes", palettes);

        return "palettesList";
    }
}
