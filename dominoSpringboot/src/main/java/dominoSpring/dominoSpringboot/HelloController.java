package dominoSpring.dominoSpringboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping ("/Hello")
    //
    public String Hello(String name) {
        return "Hello" + name ;
    }

}
