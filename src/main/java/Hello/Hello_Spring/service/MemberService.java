package Hello.Hello_Spring.service;

import Hello.Hello_Spring.domain.Member;
import Hello.Hello_Spring.repository.MemberRepository;
import Hello.Hello_Spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    /* Autowired가 있으면 멤버 리포지토리가 필요하구나 해서 스프링 컨테이너에서 꺼내서 주입.  */
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {
        //중복 이름 방지

        //optional
//        Optional<Member>result =memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원.");
//        });
        //바로 사용 가능. 메서드로 빼버리자.
        long start=System.currentTimeMillis();
        try{
            validateDuplicatedMember(member);


            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish=System.currentTimeMillis();
            long timeMs=finish-start;
            System.out.println("join time:"+timeMs);
        }

    }

    private void validateDuplicatedMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m->{
                            throw new IllegalStateException("이미 존재하는 회원 입니다.");
                        });
    }

    /* 전체 회원 조회 */
    public List<Member> findMembers() {
        long start = System.currentTimeMillis();
        try {
            return memberRepository.findAll();         }
        finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers " + timeMs + "ms");
        }
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }


}
