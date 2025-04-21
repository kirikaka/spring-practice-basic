package Hello.Hello_Spring.repository;
import Hello.Hello_Spring.domain.Member;
import java.util.*;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long id);         // id,name이 null로 반환될때 optional로 감싸서 반환하려함
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
