package dominoSpring.dominoSpringboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

// @SpringBootApplication
public class DominoSpringbootApplication {

	public static void main(String[] args) {
		// 스프링 컨테이너를 대표하는 인터페이스  ApplicationContext 에 클래스를 직접 등록
		GenericWebApplicationContext applicationContext = new GenericWebApplicationContext() {
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
		applicationContext.registerBean(HelloController.class) ;
		applicationContext.registerBean(SimpleHelloService.class) ;
		applicationContext.refresh();  //<< 스프링 컨테이너의 초기화 작업

		System.out.println("서버 기동 완료!!  ");
	}
}



