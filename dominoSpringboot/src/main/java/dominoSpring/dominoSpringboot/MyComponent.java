package dominoSpring.dominoSpringboot;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* 일반적인 어노테이션을 설정하는 방법 */
@Retention(RetentionPolicy.RUNTIME)   //이 어노테이션이 어디까지 살아있을 것인가? 런타임
@Target(ElementType.TYPE)  //이 어노테이션을 적용할 대상을 지정해주는 것, 아무데나 갖다 넣는 게 아니라 지정된 타겟 위치에만 들어감.
/* 메타 어노테이션  */
@Component   //HelloController 에 가서 @MyComponent 를 달아줌

public @interface MyComponent {
}
