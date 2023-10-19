package tobyspring.study;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class StudyController implements ApplicationContextAware {
    private final StudyService studyService;
    private final ApplicationContext applicationContext;
    public StudyController(StudyService studyService, ApplicationContext applicationContext) {
        this.studyService = studyService;
        this.applicationContext = applicationContext;
    }

    @GetMapping("/study")
    public String hello(String name) {
        return studyService.sayHello(Objects.requireNonNull(name));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(applicationContext);
        // this.applicationContext = applicationContext;
    }
}
