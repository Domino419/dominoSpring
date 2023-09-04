package dominoSpring.dominoSpringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

//@SpringBootApplication
@Configuration // 구성 정보를 가지고 있는 class라는 걸 명시
@ComponentScan // @Component 라는 어노테이션이 붙은 걸 모두 찾아서 빈으로 등록해주는 어노테이션
public class DominoSpringbootApplication {
	@Bean
	public ServletWebServerFactory servletWebServerFactory() {
		return new TomcatServletWebServerFactory() ;
	}

	@Bean
	public DispatcherServlet dispatcherServlet () {
		return new DispatcherServlet() ;
	}


	public static void main(String[] args) {
		run(DominoSpringbootApplication.class, args);
		System.out.println("서버 기동 완료!!  ");
	}

	private static void run(Class<?> applicationClass, String... args) {
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
			@Override
			protected void onRefresh() {
				super.onRefresh();

				ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class) ;
				DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class) ;
				//dispatcherServlet.setApplicationContext(this);

				WebServer webServer = serverFactory.getWebServer(servletContext -> {
					servletContext.addServlet("dispatcherServlet",dispatcherServlet)
							.addMapping("/*");
				});
				webServer.start();

			}
		} ;
		applicationContext.register(applicationClass);
		applicationContext.refresh();  //<< 스프링 컨테이너의 초기화 작업
	}
}



