package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component// 이것도 가능하지만 SpringConfig에 Bean으로 등록해주는게 좋다.
@Aspect
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") //적용범위 패키지 하위에 모두 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: "+ joinPoint.toLongString()); //어떤메서드를 콜하는지 알수있다.
        try {
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toLongString() + " " + timeMs + "ms"); //어떤메서드를 콜하는지 알수있다.
        }

    }
}
