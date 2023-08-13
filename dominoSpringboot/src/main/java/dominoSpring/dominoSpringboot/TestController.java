package dominoSpring.dominoSpringboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping ("/test")
    public String Hello(String name) {
        return "test" + "되라되라 얍얍" ;
    }

    }

