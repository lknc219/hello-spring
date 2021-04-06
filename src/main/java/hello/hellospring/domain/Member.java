package hello.hellospring.domain;

import javax.persistence.*;

@Entity //jpa가 관리해주는 Entity가 된다.
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //Oracle의 시퀀스 생각하면됨. 디비가 알아서 넣어주는 값
    private Long id; //시스템이 정하는 id

 //   @Column(name = "username") //만약 DB에 username 으로 등록되어있으면 이렇게 설정하면 매핑이 된다.
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
