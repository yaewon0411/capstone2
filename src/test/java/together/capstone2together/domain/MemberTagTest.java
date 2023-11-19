package together.capstone2together.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import together.capstone2together.service.MemberService;
import together.capstone2together.service.MemberTagService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@Rollback(value = false)
class MemberTagTest {

    @PersistenceContext
    EntityManager em;
    @Autowired
    MemberTagService memberTagService;
    @Autowired
    MemberService memberService;

    @Test
    public void 멤버태그생성(){
        List<Tag> tagList = new ArrayList<>();
        tagList.add(Tag.ADVERTISING_MARKETING);
        Member member6 = new Member("member6","3234","member6","kaka6",tagList);

        List<MemberTag> memberTags = MemberTag.create(member6);
        memberTagService.save(memberTags);
    }

}