package cc.tong.config.controller;

import cc.tong.common.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author: tn
 * @Date: 2020/8/3 0003 17:16
 * @Description:
 */
@Aspect
@Component
@Slf4j
public class Monitor {

    /**
     * 以自定义 @TqsLog 注解为切点
     */
    @Pointcut("execution(* cc.tong.system.controller..*.*(..))")
    public void monitorLog() {
    }

    @Around("monitorLog()")
    public Object doAround(ProceedingJoinPoint pjp) {

        long startTime = System.currentTimeMillis();

        try {

            Object object = pjp.proceed();
            long time = System.currentTimeMillis() - startTime;
            log.info("\u001b[31m\u001b[0m use \u001b[31m{}\u001b[0m ms [\u001b[31m{}\u001b[0m {}]"
                    , System.currentTimeMillis() - startTime
                    , pjp.getSignature().getName()
                    , pjp.getSignature().getDeclaringTypeName());

            return object;

        } catch (Throwable throwable) {
            // 输出堆栈信息
            throwable.printStackTrace();
            log.error("msgW", throwable);
            return new ResponseBean().fail();
        }
    }


}
