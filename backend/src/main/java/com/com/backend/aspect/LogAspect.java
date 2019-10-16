package com.com.backend.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Component
public class LogAspect {

    private static final Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    private final String SERVICE_POINTCUT = "execution(* com.com.backend.service..*(..))";

    @Around(SERVICE_POINTCUT)
    public Object logAdvice(ProceedingJoinPoint jp) throws Throwable {
        LOG.info("[METHOD] --------> {}", jp.getSignature().toShortString());

        Object[] signatureArgs = jp.getArgs();
        for (Object signatureArg : signatureArgs) {
            LOG.info("[ARGS] --------> {}: {}", signatureArg.getClass().getSimpleName(),
                    signatureArg.toString());
        }

        Instant startTime = Instant.now();
        Object obj = jp.proceed();
        Instant endTime = Instant.now();

        LOG.info("[METRICS] --------------------> {}, time: {} {} ", jp.getSignature().toShortString(),
                Duration.between(startTime, endTime).getNano()/1000000, "ms.");

        return obj;
    }

}
