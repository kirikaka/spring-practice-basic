package Hello.Hello_Spring.service;

import Hello.Hello_Spring.domain.Member;
import Hello.Hello_Spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository ;

    @BeforeEach
    public void beforeEach(){
        memberRepository=new MemoryMemberRepository();
        memberService=new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }




    @Test
    void 회원가입() {
        //given 먼가 주어졌을때
        Member member = new Member();
        member.setName("TestMember");


        //when 이걸 실행했는데
        Long saveId=memberService.join(member);

        //then 이게 나와야해.
        Member findMember=memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("duplicateName");

        Member member2 = new Member();
        member2.setName("duplicateName");
        //when
        memberService.join(member1);
        //내가 원하는 예외처리가 터지는면 테스트 성공으로 뜬다.
        //즉 ㅇ에러가 안뜬다
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        //에러 메시지가 뜨는지 확인
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다.");



//        try{
//            memberService.join(member2);
//        }catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다.");
//        }


        //then

    }

    @Test
    void 회원찾기() {
    }
}