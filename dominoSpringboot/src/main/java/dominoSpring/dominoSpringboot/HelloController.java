package dominoSpring.dominoSpringboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


public class HelloController {
    @GetMapping ("/Hello")   //http://localhost:8080/Hello?name=domino
    public String Hello(String name) {
        System.out.println("/Hello 잘 되는지 체크체크");
            return "Hello  " + name;
    }
}



/*
as is
@RestController
public class HelloController {
    @GetMapping ("/Hello")
    //http://localhost:8080/Hello?name=domino
    public String Hello(String name) {
        if(name != null) {
            System.out.println("나와라 얍얍") ;
            return "Hello  " + name;
        }
        else {
            System.out.println("나와라 얍얍") ;
            return "Hello  " + "익명";
        }
    }


}
 */