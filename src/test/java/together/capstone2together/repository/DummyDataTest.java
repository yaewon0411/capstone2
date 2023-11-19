package together.capstone2together.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import together.capstone2together.domain.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class DummyDataTest {

    @PersistenceContext
    EntityManager em;
    @Autowired MemberRepository memberRepository;
    @Autowired ItemRepository itemRepository;
    @Autowired ItemTagRepository itemTagRepository;
    @Autowired SurveyRepository surveyRepository;
    @Autowired QuestionRepository questionRepository;
    @Autowired RoomRepository roomRepository;
    @Autowired RoomMemberRepository roomMemberRepository;

    @Test
    public void 테스트(){
        List<Tag> tagList = new ArrayList<>();
        tagList.add(Tag.PLANNING_IDEA);
        tagList.add(Tag.ADVERTISING_MARKETING);

        Member member2 = new Member("member5","1111","member5","kaka5", tagList);
        memberRepository.save(member2);
    }
    @Test
    public void 더미데이터넣기(){

        List<Tag> tagList = new ArrayList<>();
        tagList.add(Tag.ABROAD);

        Member member2 = new Member("member2","1111","member2","kaka1", tagList);
        Member member3 = new Member("member3","1111","member3","kaka2",tagList);
        Member member4 = new Member("member4","1111","member4","kaka3",tagList);

        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);

        List<Tag> tagList2 = new ArrayList<>();
        tagList2.add(Tag.EMPLOYMENT_ENTREPRENEURSHIP);
        tagList2.add(Tag.ABROAD);
        Item item1 = Item.create("item1","This is item1","jnu","2023-11-10", tagList2);
        Item item2 = Item.create("item2","This is item2","jnu","2023-11-14", tagList2);

        itemRepository.save(item1);
        itemRepository.save(item2);

        itemTagRepository.save(ItemTag.create(item1));
        itemTagRepository.save(ItemTag.create(item2));

        Member member = new Member("오타니","password","yw","yaewon0411@email.com",tagList);
        em.persist(member);


        //설문 질문 생성
        List<String> questionList = new ArrayList<>();
        questionList.add("이름은?");
        questionList.add("나이는?");
        Question question = new Question(questionList);

        questionRepository.save(question);


        //설문 양식 생성
        Survey survey = new Survey(question);
        surveyRepository.save(survey);

        //질문 생성 완료되면 방과 연결시켜서 저장
        Room room = Room.create(item1, member,"hi","대외활동 인원 모집합니다",5 ,"jeju", survey);


        roomRepository.save(room); // -> RoomService에서 이거 하면서 같이 설문 양식이랑 설문 질문 저장시키는 방향으로 설정

    }
}
