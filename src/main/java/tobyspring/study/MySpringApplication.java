package tobyspring.study;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication
{

    public static void run(Class<?> applicationClass, String... args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(){
            @Override
            protected void onRefresh() {
                super.onRefresh();
                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
                dispatcherServlet.setApplicationContext(this);
                // Jetty, Tomcat 추상화
                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("disPatcherServlet"
                                    ,dispatcherServlet )
                            .addMapping("/*"); // 모든 요청을 다 받겠다. FrontController 책임을 맡음
                });// 웹 서버 생성함수
                // 톰캣이 실행
                webServer.start();
            }
        };
        applicationContext.register(applicationClass);
        applicationContext.refresh();
    }
}
