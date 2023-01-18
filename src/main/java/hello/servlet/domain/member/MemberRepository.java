package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
    실무에서는 동시성 문제를 고려하여 ConcurrentHashMap, AtomicLong을 사용 고려
 */
public class MemberRepository {

    private static HashMap<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 아이디가 하나씩 증가하는 시퀀스
    // static으 썼기 때문에 아무리 MemberRepository를 많이 생성해도 1개만 만들어짐.

    private static final MemberRepository instance = new MemberRepository();
    // Spring은 톰캣을 띄울 때만 쓸거라 아직 싱글톤은 안 씀

    public static MemberRepository getInstance(){
        return instance;
    }

    private MemberRepository(){

    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(sequence, member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values()); // store에 있는 모든 값을 ArrayList에 넘겨줌
                                                // 왜냐하면 new ArrayList에 값을 조작해도 store에 있는 값들에 영향을 주고싶지 않아서.
    }

    public void clearStore(){
        store.clear();
    }
}
