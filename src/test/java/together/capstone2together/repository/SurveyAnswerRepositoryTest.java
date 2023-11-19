package together.capstone2together.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import together.capstone2together.domain.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class SurveyAnswerRepositoryTest {
    @Autowired
    SurveyAnswerRepository surveyAnswerRepository;
    @Autowired RoomRepository roomRepository;
    @PersistenceContext
    EntityManager em;

//    @Test
//    void 설문답변쓰고방에지원하기(){
//
//        //멤버 찾고
//        Member member = em.find(Member.class, "member3");
//        //들어갈 방 찾고
//        Room room = em.find(Room.class, 1);
//        //방에 딸린 설문 양식 찾고
//        Survey survey = room.getSurvey();
//
//        Question question = survey.getQuestion();
//        //설문 양식의 질문들 뽑고
//        List<String> findList = question.getQuestionList();
//
//        //각 질문의 답변을 저장할 리스트 생성
//        List<String> answer = new ArrayList<>();
//        for (String s : findList) {
//            System.out.println("s = " + s);
//            answer.add("노코멘트");
//        }
//        //엔티티에 저장
//        SurveyAnswer surveyAnswer = SurveyAnswer.create(room,member,question,answer,survey);
//        surveyAnswerRepository.save(surveyAnswer);
//    }
    @Test
    void 팀장탭에서방에지원한사람들보기(){
        Member member = em.find(Member.class, "오타니");
        //List<MemberDto> findList = surveyAnswerRepository.findJoinedMemberByRoom(member.getLedRooms().get(0));
//        for (MemberDto memberDto : findList) {
//            System.out.println("memberDto = " + memberDto);
//        }
    }
    @Test
    void 팀원탭에서내가지원한목록들보기(){
//        Member member = em.find(Member.class, "member2");
//        List<RoomDto> findList = surveyAnswerRepository.findRoomByJoinedMember(member);
//        for (RoomDto roomDto : findList) {
//            System.out.println("roomDto = " + roomDto);
//        }
    }
    @Test
    void 로컬데이트넣기(){

        SurveyAnswer surveyAnswer = em.find(SurveyAnswer.class, 1);
        surveyAnswer.setLocalDateTime(LocalDateTime.now());

        SurveyAnswer surveyAnswer2 = em.find(SurveyAnswer.class, 2);
        surveyAnswer2.setLocalDateTime(LocalDateTime.now());
    }

}