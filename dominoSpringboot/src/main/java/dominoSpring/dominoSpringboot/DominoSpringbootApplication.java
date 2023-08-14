package dominoSpring.dominoSpringboot;


import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DominoSpringbootApplication {
	public static void main(String[] args) {
		System.out.println("서블릿 컨테이너 띄우기 실습 시작 ");
		TomcatServletWebServerFactory serverfactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverfactory.getWebServer(servletContext -> {
			servletContext.addServlet("hello", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					resp.setStatus(HttpStatus.OK.value());
					resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
					resp.getWriter().println("Hello Servlet");
				}
			}).addMapping("/hello");
		});
		webServer.start();

		System.out.println("서블릿 컨테이너 떳다  !  ");






	}
}



/*
 스프링부트가 없다는 걸 가정하고 코드를 작성해보자.
 Containerless 개발 준비

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DominoSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(DominoSpringbootApplication.class, args);
	}


}
*/
