package tobyspring.study;


import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import tobyspring.helloboot.HelloController;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StudyApplication {
    public static void main(String[] args){
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
        applicationContext.registerBean(StudyController.class);
        applicationContext.registerBean(SimpleStudyService.class);
        applicationContext.refresh();

        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

        // Jetty, Tomcat 추상화
        WebServer webServer = serverFactory.getWebServer(servletContext -> {

            servletContext.addServlet("disPatcherServlet", new DispatcherServlet(applicationContext) {

            }).addMapping("/*"); // 모든 요청을 다 받겠다. FrontController 책임을 맡음
        });// 웹 서버 생성함수
        // 톰캣이 실행

        webServer.start();

    }
}
