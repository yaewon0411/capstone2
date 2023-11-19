package together.capstone2together.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import together.capstone2together.dto.TagListDto;
import together.capstone2together.repository.MemberRepository;
import together.capstone2together.service.MemberService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberTest {
    @PersistenceContext EntityManager em;
    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;
    private Member findOne;

    @Test
    public void 멤버생성테스트(){
        List<Tag> tagList = new ArrayList<>();
        tagList.add(Tag.ADVERTISING_MARKETING);
        Member member6 = new Member("member6","3234","member6","kaka6",tagList);
        memberRepository.save(member6);
//        Member member2 = new Member("member2","1111","member2","kaka1");
//        Member member3 = new Member("member3","1111","member3","kaka2");
//        Member member4 = new Member("member4","1111","member4","kaka3");
//
//        memberRepository.save(member2);
//        memberRepository.save(member3);
//        memberRepository.save(member4);
//
//        Member findOne = em.find(Member.class, "member2");
//        assertThat(member2).isEqualTo(findOne);
    }
    @Test
    public void 회원가입(){
//        Member member = new Member("yaewon0411","password","jnu","kakaoID");
//        member.getTagList().add(Tag.GAME_SOFTWARE);
//        member.getTagList().add(Tag.EMPLOYMENT_ENTREPRENEURSHIP);
//
//        memberService.join(member);
    }
    @Test
    public void 닉네임수정테스트(){
        Member findOne = em.find(Member.class, "yaewon0411");

        memberRepository.updateName(findOne.getId(), "www");
        System.out.println("findOne.getName() = " + findOne.getName());
    }
    @Test
    public void 비밀번호변경테스트(){
        Member findOne = em.find(Member.class, "yaewon0411");
        memberService.changePw(findOne, "newPwww");

    }
    @Test
    public void 회원태그넣기테스트(){
        Member findOne = em.find(Member.class, "member2");
        findOne.getTagList().add(Tag.ABROAD);
    }




}