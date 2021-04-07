package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {



    //Configuration 한것도 스프링에서 관리해주기 때문에 Bean 으로 관리가 된다.

    //애너테이션 없이 DI 해준다. (의존성 주입)
    //스프링데이터JPA가 인터페이스를 자동으로 구현해준 것을 주입받아 사용할 수 있다.
    private final MemberRepository memberRepository;

    //스프링컨테이너에서 memberRepository 를 찾는데 등록되있는게 없는것 같지만
    //스프링데이터JPA가 자동으로 구현해준 SpringDataJpaMemberRepository가 있기때문에 자동으로 주입 할 수 있다.
    //extends JpaRepository<Member,Long> 덕분이다.
    @Autowired //생성자가 하나일경우 생략가능
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*  @Autowired //dataSource 쓰는곳에 필요했던 것.
    public SpringConfig(DataSource datasource) {
        this.datasource = datasource;
    }*/

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }

//    @Bean
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(datasource); // DataResource Repository로 바꿔줌
//        return new JdbcTemplateMemberRepository(datasource);
//        return new JpaMemberRepository(em);
//    }
}
