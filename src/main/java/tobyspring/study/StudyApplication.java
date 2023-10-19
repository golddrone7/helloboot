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
import tobyspring.helloboot.HelloController;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StudyApplication {
    public static void main(String[] args){
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.registerBean(StudyController.class);
        applicationContext.refresh();

        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

        // Jetty, Tomcat 추상화
        WebServer webServer = serverFactory.getWebServer(servletContext -> {

            servletContext.addServlet("frontcontroller", new HttpServlet(){
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
                    // 인증, 보안, 다국어, 공통 기능
                    if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
                        String name = req.getParameter("name");
                        // 상태코드, 컨텐츠타입 header, Body
                        // 200 정상
                        StudyController studyController = applicationContext.getBean(StudyController.class);
                        String ret = studyController.hello(name);

                        resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
                        resp.getWriter().println("Hellos " + ret);

                    }
                    else if (req.getRequestURI().equals("/user")){

                    } else {
                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                    }


                }
            }).addMapping("/*"); // 모든 요청을 다 받겠다. FrontController 책임을 맡음
        });// 웹 서버 생성함수
        // 톰캣이 실행

        webServer.start();

    }
}
