package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//인터페이스의 다중상속으로 원래 해야하는 MemberRepository와 JpaRepository를 상속한다
//JpaRepository의 제네릭에는 사용되는 도메인(데이터 객체)과, 해당 테이블 PK의 데이터타입이 들어간다.
//스프링데이터JPA는 인터페이스 구현으로 개발을 완료할 수 있다.
//스프링데이터JPA가 JpaRepository를 상속받고 있으면 구현체를 자동으로 만들어주고 스프링빈에 자동으로 등록해주므로 가져다 쓰기만 하면 된다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>,MemberRepository  {
    //모두에게 통용되는 쿼리는 제공 가능하지만 비즈니스 마다 다른 컬럼명 등을 조회하려면
    //규칙에 맞는 findBy~ 로 인터페이스를 만들어주면 jpa가 해당 메서드를 구현해준다. (단순쿼리)
    @Override
    Optional<Member> findByName(String name);
}
