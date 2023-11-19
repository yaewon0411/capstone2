package together.capstone2together.repository;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import together.capstone2together.domain.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class RoomRepositoryTest {
    @PersistenceContext
    EntityManager em;

    @Autowired RoomRepository roomRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired SurveyRepository surveyRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemTagRepository itemTagRepository;
    @Autowired
    QuestionRepository questionRepository;

    @Test
    void 방만들기테스트2() {
        List<String> questionList = new ArrayList<>();
        questionList.add("이름은?");
        questionList.add("나이는?");
        questionList.add("전공은?");
        Question question = new Question(questionList);
        Question save = questionRepository.save(question);
        Survey survey = new Survey(save);
        Survey findSurvey = surveyRepository.save(survey);

        Item findItem = em.find(Item.class, 5);
        Member findMember = em.find(Member.class, "member2");
        Room room = Room.create(findItem,findMember,"테스트 방", "테스트 방 소개글",3,"서울",findSurvey);
        roomRepository.save(room);

    }

    @Test
    void 방만들기테스트(){
        Item item1 = em.find(Item.class, 1);
        List<Tag> tagList = new ArrayList<>();
        tagList.add(Tag.ARCHITECTURE_CONSTRUCTION_INTERIOR);
        Member member = new Member("오타니","password","yw","yaewon0411",tagList);
        em.persist(member);
        //설문 질문 생성
        List<String> questionList = new ArrayList<>();
        questionList.add("이름은?");
        questionList.add("나이는?");
        Question question = new Question(questionList);

        questionRepository.save(question);
        Survey survey = new Survey(question);
        surveyRepository.save(survey);

        //질문 생성 완료되면 방과 연결시켜서 저장
        Room room = Room.create(item1, member,"hi","대외활동 인원 모집합니다",5 ,"jeju", survey);
        roomRepository.save(room); // -> RoomService에서 이거 하면서 같이 설문 양식이랑 설문 질문 저장시키는 방향으로 설정
    }

    @Test
    void findByCreator() {

        Member findOne = em.find(Member.class, "yaewon0411");

        List<Room> findList = roomRepository.findByMember(findOne);
        for (Room room : findList) {
            System.out.println("room = " + room.getMember().getName());
        }

    }

}