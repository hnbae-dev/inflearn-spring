package hello.core.member;

import java.util.HashMap;
import java.util.Map;

// DB 확정X 가정 -> 메모리 회원 저장소 구현
public class MemoryMemberRepository implements MemberRepository {

    // 실무: 동시성 이슈 발생 가능 -> ConcurrentHashMap 사용
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
