package together.capstone2together.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.jboss.resteasy.annotations.ResponseObject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import together.capstone2together.domain.Item;
import together.capstone2together.domain.Member;
import together.capstone2together.domain.Pick;
import together.capstone2together.service.PickService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class PickRepositoryTest {

    @Autowired PickRepository pickRepository;
    @Autowired
    PickService pickService;
    @PersistenceContext
    EntityManager em;
    @Test
    void findByMember() {

        Item item = em.find(Item.class, 1);
        Member member = em.find(Member.class, "오타니");

        Pick pick = Pick.create(member, item);
        pickService.save(pick);

        List<Item> findList = pickRepository.findByMember(member);
        for (Item item1 : findList) {
            System.out.println("item1 = " + item1);
        }
        assertThat(findList.size()).isEqualTo(1);
    }
}