package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {
//class영역에서 ctrl+shift+T
    private final MemberRepository memberRepository;

    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        //같은 이름 중복회원X crtl+alt+v Optional 생성
        //과거에는 if==null 로 코딩했지만 지금은 Optional 로 한번 감싸서 반환해준다. 덕분에 ifPresent를 사용할수있고 Get으로 꺼낼수있고
        //orElseGet 값이 있으면 꺼내고 없으면 여기있는 메소드를 실행하거나 Default 값을 넣어서 꺼내거나 할 수 있다. (더 찾아보기)
        //Optional을 바로 반환하는건 좋지않다 ex)Optional<Member> result = memberRepository.findByName(member.getName());
        //로직이 나올경우 메소드로 뽑는게 좋다. ctrl+shift+atl+T
        //Service는 비즈니스적에 의존적으로, Repository 같은 경우 좀 더 기계적이고 단순한 용어로 만든다.
        validateDuplicateMember(member);//중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m ->{ //ifPresent 이미 값이 있으면 (옵셔널이기때문에 가능함)
                throw new IllegalStateException("이미 존재하는 회원입니다.");
             });
    }

    /**
     * 전체회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * 회원 아이디로 조회
     * @param memberId
     * @return
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
