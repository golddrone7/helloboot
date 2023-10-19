package tobyspring.study;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class SimpleStudyService implements StudyService {

    @Override
    public String sayHello(String name){
        return "Hello " + name;
    }
}
