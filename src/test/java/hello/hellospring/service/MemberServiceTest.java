package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    //테스트는 메서드명을 한글로 바꿔서 해도 된다.

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    //각 테스트를 실행하기 전 실행하는 메서드
    //MemberService를 생성과 동시에 만든 memberRepository를 재사용 하기 위함
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); //DI Dependencies Injection 의존성 주입
    }

    //각 테스트를 마치고 실행하는 메서드(DB역할 Repository를 clean 해준다)
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

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