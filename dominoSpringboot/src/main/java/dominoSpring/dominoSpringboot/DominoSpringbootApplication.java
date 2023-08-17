package dominoSpring.dominoSpringboot;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SpringBootApplication
public class DominoSpringbootApplication {

	public static void main(String[] args) {
		// 스프링 컨테이너를 대표하는 인터페이스  ApplicationContext 에 클래스를 직접 등록
		GenericApplicationContext applicationContext = new GenericApplicationContext();
		applicationContext.registerBean(HelloController.class) ;
		applicationContext.refresh();   // <- 등록하고 나서 초기화 처리

		TomcatServletWebServerFactory serverfactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverfactory.getWebServer(servletContext -> {

			servletContext.addServlet("frontController", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					// 인증, 보안, 다국어 처리, 공통 기능
					if(req.getRequestURI().equals("/Hello")&& req.getMethod().equals(HttpMethod.GET.name())){
						String name = req.getParameter("name");

						HelloController HelloController  = applicationContext.getBean(HelloController.class) ;
						String ret = HelloController.Hello(name) ;

						resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
						resp.getWriter().println("::::DominoSpringbootApplication __ Hello__ " );
						resp.getWriter().println(ret);
					}
					else { // 404 error
						resp.getWriter().println("::::DominoSpringbootApplication __ 404 error");
						resp.setStatus(HttpStatus.NOT_FOUND.value());
					}
				}
			}).addMapping("/*");   //요청이 들어오는 모든 것을 Control할꺼야.
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
