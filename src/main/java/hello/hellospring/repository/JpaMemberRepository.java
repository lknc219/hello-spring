package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.hibernate.annotations.common.reflection.XMember;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    //JPA는 엔티티매니저라는걸로 모든걸 동작시킨다. JPA를 쓰려면 선언해주고 주입받아 쓰면된다
    //gradle에서 implementation 해주고 application.properties에 설정해준 jpa 설정들을 토대로 스프링이 내부에서
    //자동으로 객체를 생성해준다. 그래서 우리는 가져다 쓰기만 하면 된다 (인잭션)
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //영속하다 영구저장하다 persist 저장(insert)할때 쓰는 메서드 인듯.?
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); //조회할 타입과 식별자 넣어주면 끝.
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        // jpql 을 짠다. jpa에서 쓰는 sql인가?
        // pk기반으로 찾는게 아니면 jqpl 이라는걸 작성해줘야한다.
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
