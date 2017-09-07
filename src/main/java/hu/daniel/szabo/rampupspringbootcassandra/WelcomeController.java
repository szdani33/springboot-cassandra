package hu.daniel.szabo.rampupspringbootcassandra;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @RequestMapping(value="/*")
    public String welcome() {
        return "Welcome!";
    }
}
