package tobyspring.study;


import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StudyApplication {
    public static void main(String[] args){
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

        // Jetty, Tomcat 추상화
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("study", new HttpServlet(){
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
                    // 상태코드, 컨텐츠타입 header, Body
                    // 200 정상
                    resp.setStatus(200);
                    resp.setHeader("Content-Type", "text/plain");
                    resp.getWriter().println("Hello Servlet");
                }
            }).addMapping("/study");
        });// 웹 서버 생성함수
        // 톰캣이 실행

        webServer.start();

    }
}
