package dominoSpring.dominoSpringboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

//@SpringBootApplication
@Configuration // 구성 정보를 가지고 있는 class라는 걸 명시
public class DominoSpringbootApplication {
	@Bean
	public HelloController helloController(HelloService helloService) {
		return new HelloController(helloService);
	}

	@Bean
	public HelloService helloService(){
		return new SimpleHelloService() ;  // 메소드의 리턴 타입은 이 빈을 의존 오브젝트로 주입 받을 다른 빈에서 참조하는 타입(주로 인터페이스)으로 지정
	}

	public static void main(String[] args) {
		// 스프링 컨테이너를 대표하는 인터페이스  ApplicationContext 에 클래스를 직접 등록
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
			@Override
			protected void onRefresh() {
				super.onRefresh();

				ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
				WebServer webServer = serverFactory.getWebServer(servletContext -> {
					servletContext.addServlet("dispatcherServlet",
									new DispatcherServlet(this))
							.addMapping("/*");
				});
				webServer.start();

			}
		} ;
		applicationContext.register(DominoSpringbootApplication.class);
		applicationContext.refresh();  //<< 스프링 컨테이너의 초기화 작업

		System.out.println("서버 기동 완료!!  ");
	}
}



