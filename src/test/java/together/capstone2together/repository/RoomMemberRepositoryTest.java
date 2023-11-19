package together.capstone2together.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import together.capstone2together.domain.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class RoomMemberRepositoryTest {

    @PersistenceContext
    EntityManager em;
    @Autowired
    RoomMemberRepository roomMemberRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 방에지원하기(){

        //참여할 방 찾기
        Room room = em.find(Room.class, 102);

        Member member2 = em.find(Member.class, "member2");
        Member member3 = em.find(Member.class, "member3");
        Member member4 = em.find(Member.class, "member4");
        //방에 참여하기
        RoomMember roomMember = RoomMember.create(room,member2);
        RoomMember roomMember2 = RoomMember.create(room,member3);
        RoomMember roomMember3= RoomMember.create(room,member4);

        roomMemberRepository.save(roomMember);
        roomMemberRepository.save(roomMember2);
        roomMemberRepository.save(roomMember3);
    }
    @Test
    void 멤버가지원한방리스트() {

        Member findOne = em.find(Member.class, "member1");
        List<RoomMember> findList = roomMemberRepository.findByMember(findOne);

        for (RoomMember roomMember : findList) {
            System.out.println("roomMember.getMember() = " + roomMember.getMember().getName());
            System.out.println("roomMember.getRoom() = " + roomMember.getRoom().getTitle());
            System.out.println("roomMember.getId() = " + roomMember.getId());
        }
    }


}