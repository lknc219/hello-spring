package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    //설정파일을 보고 데이터소스 빈을 만들어줌
   // private DataSource datasource;

    //Configuration 한것도 스프링에서 관리해주기 때문에 Bean 으로 관리가 된다.

    //애너테이션 없이 DI 해준다. (의존성 주입)
    private EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }

     /*  @Autowired //dataSource 쓰는곳에 필요했던 것.
    public SpringConfig(DataSource datasource) {
        this.datasource = datasource;
    }*/

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(datasource); // DataResource Repository로 바꿔줌
//        return new JdbcTemplateMemberRepository(datasource);
        return new JpaMemberRepository(em);
    }
}
