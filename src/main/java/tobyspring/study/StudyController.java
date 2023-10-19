package tobyspring.study;

import java.util.Objects;

public class StudyController {
    private final StudyService studyService;

    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }

    public String hello(String name) {
        return studyService.sayHello(Objects.requireNonNull(name));
    }
}
