package tobyspring.study;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 언제까지 유지될 것인가?
@Retention(RetentionPolicy.RUNTIME)
// 타겟 위치를 결정해줌
@Target(ElementType.TYPE)
@Component
public @interface MyComponent {
}
