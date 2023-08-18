package dominoSpring.dominoSpringboot;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SpringBootApplication
public class DominoSpringbootApplication {

	public static void main(String[] args) {
		// 스프링 컨테이너를 대표하는 인터페이스  ApplicationContext 에 클래스를 직접 등록
		GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
		applicationContext.registerBean(HelloController.class) ;
		applicationContext.registerBean(SimpleHelloService.class) ;
		applicationContext.refresh();   // <- 등록하고 나서 초기화 처리

		TomcatServletWebServerFactory serverfactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverfactory.getWebServer(servletContext -> {
			servletContext.addServlet("dispatcherServlet",
					new DispatcherServlet(applicationContext)
			).addMapping("/*");
		});
		webServer.start();
		System.out.println("서버 기동 완료!!  ");
	}
}



