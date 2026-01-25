package projecttest.javaspringtest.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component // 스프링 빈으로 등록
@Aspect    // AOP Aspect임을 명시
public class LogAspect {

    /**
     * <h2> Advice의 종류 (실행 시점) </h2>
     * {@code @Before} : 타겟 메소드 실행 전에 수행. <br>
     * {@code @AfterReturning} : 타겟 메소드가 성공적으로 결과값을 반환한 후에 수행. <br>
     * {@code @AfterThrowing} : 타겟 메소드 수행 중 예외가 발생했을 때 수행. <br>
     * {@code @After} : 성공/실패 여부와 상관없이 메소드 실행 후 무조건 수행 (Java의 finally와 유사). <br>
     * {@code @Around} : (가장 강력함) 메소드 실행 전과 후 모두 제어 가능. 메소드 실행 자체를 가로채서 직접 실행하거나 실행하지 않을 수도 있음. <br><br>
     *
     * <h2> Pointcut 표현식 (적용 범위 지정) </h2>
     * {@code @Around} 같은 곳에 괄호 안에 들어가는 식이 Pointcut 표현식.
     * <br>
     * {@code execution} : 가장 일반적인 형태 <br>
     * {@code execution(* com.example.service..*(..))}  해석 : com.example.service 패키지 하위(..)의 모든 클래스, 모든 메소드(*), 모든 파라미터((..))에 적용.<br>
     * {@code within} : 특정 타입(클래스/인터페이스) 내부의 메소드 <br>
     * {@code within(com.example.service.UserService)} <br>
     * {@code @annotation} : 특정 어노테이션이 붙은 메소드 <br>
     * {@code @annotation(com.example.annotation.LogExecutionTime)} <br>
     */
    // @LogExecutionTime 어노테이션이 붙은 메소드에 적용
    @Around("@annotation(LogExecutionTime) && bean(*Service)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        System.out.println("메서드 시작 : " + joinPoint.toString());

        try {
            // 실제 타겟 메소드 실행 (proceed를 호출해야 원래 로직이 돌아감)
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("메서드 종료 : " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }

    // ═══════════════════════════════════════════════════════════════
    //                      Pointcut 표현식 예시, execution [일반적으로 Advice 에너테이션 안에 Pointcut을 작성해도됨. 밑에는 @Pointcut 으로 예시만 작성한것.]
    // ═══════════════════════════════════════════════════════════════

    // 모든 public 메서드
    @Pointcut("execution(public * *(..))")
    public void allPublicMethods() {}

    // service 패키지의 모든 메서드
    @Pointcut("execution(* projecttest.javaspringtest.AOP.*.*(..))")
    public void serviceMethods() {}

    // service 패키지와 하위 패키지의 모든 메서드
    @Pointcut("execution(* projecttest.javaspringtest.AOP..*.*(..))")
    public void serviceAndSubPackageMethods() {}

    // String을 반환하는 모든 메서드
    @Pointcut("execution(String *(..))")
    public void stringReturnMethods() {}

    // 파라미터가 없는 메서드
    @Pointcut("execution(* *())")
    public void noParamMethods() {}

    // 첫 번째 파라미터가 String인 메서드
    @Pointcut("execution(* *(String, ..))")
    public void firstParamStringMethods() {}

    // 정확히 2개의 파라미터를 받는 메서드
    @Pointcut("execution(* *(*, *))")
    public void twoParamMethods() {}

    // ═══════════════════════════════════════════════════════════════
    //                      within 표현식
    // ═══════════════════════════════════════════════════════════════

    // 특정 클래스 내 모든 메서드
    @Pointcut("within(projecttest.javaspringtest.AOP.LogAopTestService)")
    public void withinUserService() {}

    // 특정 패키지 내 모든 클래스의 모든 메서드
    @Pointcut("within(projecttest.javaspringtest.AOP.*)")
    public void withinServicePackage() {}

    // ═══════════════════════════════════════════════════════════════
    //                      @annotation 표현식
    // ═══════════════════════════════════════════════════════════════

    // 특정 어노테이션이 붙은 메서드
    @Pointcut("@annotation(projecttest.javaspringtest.AOP.LogExecutionTime)")
    public void loggableMethods() {}

    // @Transactional 어노테이션이 붙은 메서드
    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void transactionalMethods() {}

    // ═══════════════════════════════════════════════════════════════
    //                      bean 표현식
    // ═══════════════════════════════════════════════════════════════

    // 특정 빈 이름의 모든 메서드
    @Pointcut("bean(userService)")
    public void userServiceBean() {}

    // 이름이 Service로 끝나는 빈의 모든 메서드
    @Pointcut("bean(*Service)")
    public void serviceBeans() {}

    // ═══════════════════════════════════════════════════════════════
    //                      조합 표현식
    // ═══════════════════════════════════════════════════════════════

    // AND 조합
    @Pointcut("execution(* projecttest.javaspringtest.AOP.*.*(..)) && @annotation(LogExecutionTime)")
    public void loggableServiceMethods() {}

    // OR 조합
    @Pointcut("execution(* projecttest.javaspringtest.AOP.*.*(..)) || execution(* projecttest.javaspringtest.Controller.*.*(..))")
    public void serviceOrRepositoryMethods() {}

    // NOT
    @Pointcut("execution(* projecttest.javaspringtest.AOP.*.*(..)) && !execution(* *.get*(..))")
    public void serviceMethodsExceptGetters() {}
}
