package hello.hello_spring.domain;

public class Member {

    private Long id; // 시스템이 저장하는 회원 구분용 임의의 값
    private String name; // 고객이 회원가입 할 때 적는 이름

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
