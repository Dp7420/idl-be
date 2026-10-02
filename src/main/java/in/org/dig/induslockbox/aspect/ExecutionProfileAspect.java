package in.org.dig.induslockbox.aspect;

import in.org.dig.induslockbox.utils.ApplicationLogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class ExecutionProfileAspect {

    private static final ThreadLocal<Boolean> profilingActive = ThreadLocal.withInitial(()->false);
    private static final ThreadLocal<List<String>> methodLogs = ThreadLocal.withInitial(ArrayList::new);

    @Pointcut("@annotation(in.org.dig.induslockbox.aspect.ProfileExecution)")
    public void profileExecutionAnnotation() {

    }
    @Pointcut("execution(* com.orchasp..*(..))")
    public void applicationMethodPointcut() {

    }

    @Around("profileExecutionAnnotation()]")
    public Object logControllerAndMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        profilingActive.set(true);
        methodLogs.get().clear();

        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long totalTime = System.currentTimeMillis() - start;
            ApplicationLogger.LOGGER.info("\n=== Execution Report for {}===", joinPoint.getSignature());
            for(String log: methodLogs.get()) {
                ApplicationLogger.LOGGER.info(log);
            }
            ApplicationLogger.LOGGER.info("Total Time for request: {} ms", totalTime);
            return result;
        } finally {
            methodLogs.remove();
            profilingActive.remove();
        }
    }

    @Around("applicationMethodPointcut() && !within(in.org.dig.induslockbox.aspect.ExecutionProfileAspect)")
    public Object logMethodTime(ProceedingJoinPoint joinPoint) throws Throwable {
        if(!Boolean.TRUE.equals(profilingActive.get())) {
            return joinPoint.proceed();
        }
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long totalTime = System.currentTimeMillis() - start;

        methodLogs.get().add(joinPoint.getSignature() + " took " + totalTime + " ms");
        return result;
    }
}
