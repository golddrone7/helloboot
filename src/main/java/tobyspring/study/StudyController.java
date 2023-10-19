package tobyspring.study;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RequestMapping("/hello")
public class StudyController {
    private final StudyService studyService;

    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }

    @ResponseBody
    @GetMapping
    public String hello(String name) {
        return studyService.sayHello(Objects.requireNonNull(name));
    }
}
