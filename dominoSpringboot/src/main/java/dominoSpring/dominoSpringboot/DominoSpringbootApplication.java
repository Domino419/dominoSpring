package dominoSpring.dominoSpringboot;


import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
			// servletContext.addServlet("hello", new HttpServlet() {
			servletContext.addServlet("frontController", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					// 인증, 보안, 다국어 처리, 공통 기능 -  매핑 기능을 컨트롤러가 담당.

					if(req.getRequestURI().equals("/Hello")&& req.getMethod().equals(HttpMethod.GET.name())){
						String name = req.getParameter("name");

						resp.setStatus(HttpStatus.OK.value());
						resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE); // <-- 하드코딩 하지 말고 스프링 안에 정의되어 있는 상수 사용 권유
						resp.getWriter().println(":::: Hello__ " + name );
					}
					else if (req.getRequestURI().equals("/user")) {
						//
						resp.getWriter().println(":::: /user__ ");
					}
					else {
						// 404 error
						resp.getWriter().println(":::: 404 error");
						resp.setStatus(HttpStatus.NOT_FOUND.value());
					}

				}
			// }).addMapping("/Servlet");
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
