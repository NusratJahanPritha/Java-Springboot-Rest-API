package demo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* demo.ShoppingCart.checkout(..))")
    public void beforeLogger(JoinPoint jp){
        String arg=jp.getArgs()[0].toString();
//        System.out.println(jp.getSignature());
        System.out.println("before loggers:"+arg);
    }
    @Before("execution(* demo.ShoppingCart.quantity(..))")
    public void beforeRet(JoinPoint jp){
        String arg=jp.getArgs()[0].toString();
//        System.out.println(jp.getSignature());
        System.out.println("before loggers:"+arg);
    }

    @After("execution(* *.*.checkout(..))")
    public void afterLogger(){
        System.out.println("after loggers");
    }
    @Pointcut("execution(* demo.ShoppingCart.quantity(..))")
    public void afterRetPointcut(){}

    @AfterReturning(pointcut = "afterRetPointcut()",returning = "retVal")
    public void afterReturn(String retVal){
        System.out.println("After return"+ retVal);
    }
    @Before("afterRetPointcut()")
    public void returnval(){
        System.out.println("returning value");
    }
}
