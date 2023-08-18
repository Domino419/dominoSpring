package dominoSpring.dominoSpringboot;

import java.util.Objects;


public class HelloController {
    private final HelloService helloService ;

    // 7 line에서 Problems 뜬 거 클릭해서 add constructor parameter
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }


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