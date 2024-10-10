package hello.hello_spring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest // 스프링 컨테이너와 테스트를 함께 실행
@Transactional
/*
테스트 케이스에 이 어노테이션이 있으면, 테스트 시작 전 트랜잭션을 시작하고,
테스트 완료 후 항상 롤백한다.
-> DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다.
 */
class MemberServiceIntegrationTest {
    
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() {
        // given - 주어진 상황
        Member member = new Member();
        member.setName("spring");
        
        // when - 이를 실행했을 때, (검증하고 싶은 내용)
        Long saveId = memberService.join(member);

        // then - 원하는 결과
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
            () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}