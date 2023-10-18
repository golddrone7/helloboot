package tobyspring.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Rest 방식을 이용 응답을 바디에 특정한 타입을 인코딩해서 하는 방식
@RestController
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    // http 메서드가 GET으로만 되어있는 것만 받겠다.
    @GetMapping("/hello")
    public String hello(String name) {  // 메서드 파라미터를 지정하면 전달함
        if (name == null || name.trim().length() == 0) throw new IllegalArgumentException();

        return helloService.sayHello(name);
    }

    @GetMapping("/bye")
    public String bye(){
        System.out.println("ㅎㅇㅎㅇ");
        return "Hello ";
    }

    @GetMapping("/count")
    public String count(String name) {
        return name + ": " + helloService.countOf(name);
    }
}
