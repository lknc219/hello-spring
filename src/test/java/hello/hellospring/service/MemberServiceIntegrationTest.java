package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
//스프링 컨테이너와 테스트를 함께 실행한다
//테스트 케이스에 애너테이션이 있으면, 테스트 시작 전 트랜잭션 시작, 테스트 완료 후 항상 롤백한다.
//이렇게 하면 DB에 데이터가 남지 않아서 다음 테스트에 영향을 주지 않는다.
//@Commit 을 해주는 애너테이션도 있다.
//이전에 했던 하나하나 테스트가 단위테스트, 스프링컨테이너와 DB연결 테스트는 통합테스트인데
//단위테스트가 좀 더 좋은 테스트일 확률이 높다.
@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    //기존의 Constructor 로 했던 인잭션 말고 Spring 에서 관리해주는 Bean을 활용해 테스트를 진행한다

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given (주어짐)
        Member member = new Member();
        member.setName("spring");

        //when (실행했을떄)
        Long saveId = memberService.join(member); //return 단축키 ctrl+alt+v

        //then (결과)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
/*
        try {
            memberService.join(member2);
            fail("예외가 발생해야합니다");
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/

        //than
        //memberService.join(member2) 로직을 태울떄, IllegalStateException 예외가 실행되는지 확인하는 assertThrows
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers(){

    }

    @Test
    void findOne() {
    }
}