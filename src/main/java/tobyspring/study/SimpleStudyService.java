package tobyspring.study;

public class SimpleStudyService implements StudyService {

    @Override
    public String sayHello(String name){
        return "Hello " + name;
    }
}
