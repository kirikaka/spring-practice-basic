package Hello.Hello_Spring.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    @Around("execution(*Hello.Hello_Spring..*(..())")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("Start: " + joinPoint.toString());

        try{
            return joinPoint.proceed();
        } finally {
            long end = System.currentTimeMillis();
            long timeMs=end - start;

            System.out.println("End: " + joinPoint.toString() +" Time: " + timeMs + " ms");
        }
    }

}
