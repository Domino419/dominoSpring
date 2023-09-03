package dominoSpring.dominoSpringboot;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Objects;

@RestController
public class HelloController  {
    private final HelloService helloService ;
    private final ApplicationContext applicationContext ;

    public HelloController (HelloService helloService,ApplicationContext applicationContext){
        this.helloService = helloService ;
        this.applicationContext = applicationContext ;

        System.out.println(applicationContext);
    }

    @GetMapping("/hello")
    public String Hello(String name) {
        System.out.println("HelloController::: say Hello 되는지 체크체크 ");
        return helloService.sayHello(Objects.requireNonNull(name));
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