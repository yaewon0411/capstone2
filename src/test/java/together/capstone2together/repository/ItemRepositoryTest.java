package together.capstone2together.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import together.capstone2together.domain.Item;
import together.capstone2together.domain.ItemTag;
import together.capstone2together.domain.Tag;
import together.capstone2together.service.ItemService;

import javax.swing.text.TabableView;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class ItemRepositoryTest {
    @Autowired ItemRepository itemRepository;
    @Autowired ItemTagRepository itemTagRepository;
    @Autowired
    ItemService itemService;
    @PersistenceContext
    EntityManager em;

    @Test
    public void 실시간인기활동추출테스트(){

        List<Tag> tagList = new ArrayList<>();
        tagList.add(Tag.EXTRACURRICULAR_SUPPORTERS);
        tagList.add(Tag.GAME_SOFTWARE);
        tagList.add(Tag.PLANNING_IDEA);

        Item item4 = Item.create("item4","This is item4","jnu","2023-11-23", tagList);
        Item item5 = Item.create("item5","This is item5","jnu","2023-11-04", tagList);
        Item item3 = Item.create("item3","This is item3","jnu","2023-12-11", tagList);
        
        item4.setViews(10);
        item5.setViews(3);
        item3.setViews(8);

        itemRepository.save(item4);
        itemRepository.save(item5);
        itemRepository.save(item3);

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String day = currentTime.format(formatter);//이거 수정해야 함. 원하는 형식으로 안나와

        List<Item> top20ByViews = itemRepository.findTop20ByViews(day);
        for (Item top10ByView : top20ByViews) {
            System.out.println("top10ByView = " + top10ByView);
        }
    }
    @Test
    public void 제목으로아이템찾기테스트(){

        List<Tag> tagList = new ArrayList<>();
        tagList.add(Tag.EMPLOYMENT_ENTREPRENEURSHIP);
        tagList.add(Tag.ABROAD);
        Item item1 = Item.create("item1","This is item1","jnu","2023-11-10", tagList);
        Item item2 = Item.create("item2","This is item2","jnu","2023-11-14", tagList);

        itemRepository.save(item1);
        itemRepository.save(item2);

        itemTagRepository.save(ItemTag.create(item1));
        itemTagRepository.save(ItemTag.create(item2));


        List<Item> items = itemRepository.findByTitle("item1");
        for (Item item : items) {
            System.out.println("item = " + item);
        }
    }
    @Test
    public void 마감기한임박아이템출력(){

        List<Tag> tagList = new ArrayList<>();
        tagList.add(Tag.EMPLOYMENT_ENTREPRENEURSHIP);
        tagList.add(Tag.ABROAD);
        Item item1 = Item.create("item1","This is item1","jnu","2023-11-10", tagList);
        Item item2 = Item.create("item2","This is item2","jnu","2023-11-14", tagList);
        Item item3 = Item.create("item3","This is item3","jnu","2023-11-16", tagList);
        Item item4 = Item.create("item4","This is item4","jnu","2023-11-02", tagList);
        Item item5 = Item.create("item5","This is item5","jnu","2023-11-01", tagList);

        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
        itemRepository.save(item4);
        itemRepository.save(item5);

        itemTagRepository.save(ItemTag.create(item1));
        itemTagRepository.save(ItemTag.create(item2));
        itemTagRepository.save(ItemTag.create(item3));
        itemTagRepository.save(ItemTag.create(item4));
        itemTagRepository.save(ItemTag.create(item5));
//
//        List<Item> byDeadline = itemJpaRepository.findTop20ByDeadline();
//        for (Item item : byDeadline) {
//            System.out.println("item = " + item);
//        }
//
//
        
//        List<Item> byDeadline = itemService.getImminentDeadline();


    }
    @Test
    public void 가장최근에추가된20개아이템뽑기(){

//        List<Tag> tagList = new ArrayList<>();
//        tagList.add(Tag.EMPLOYMENT_ENTREPRENEURSHIP);
//        tagList.add(Tag.ABROAD);
//        Item item1 = Item.create("item1","This is item1","jnu","2023-11-10", tagList);
//        Item item2 = Item.create("item2","This is item2","jnu","2023-11-14", tagList);
//        Item item3 = Item.create("item3","This is item3","jnu","2023-11-16", tagList);
//        Item item4 = Item.create("item4","This is item4","jnu","2023-11-02", tagList);
//        Item item5 = Item.create("item5","This is item5","jnu","2023-11-01", tagList);
//
//        itemRepository.save(item1);
//        itemRepository.save(item2);
//        itemRepository.save(item3);
//        itemRepository.save(item4);
//        itemRepository.save(item5);
//
//        itemTagRepository.save(ItemTag.create(item1));
//        itemTagRepository.save(ItemTag.create(item2));
//        itemTagRepository.save(ItemTag.create(item3));
//        itemTagRepository.save(ItemTag.create(item4));
//        itemTagRepository.save(ItemTag.create(item5));
//
//        List<Item> top20ByIdDesc = itemRepository.findTop20ByOrderByIdDesc();
//        for (Item item : top20ByIdDesc) {
//            System.out.println("item = " + item);
//        }

    }
    @Test
    public void 관심태그리스트로아이템리스트업(){
        List<Tag> tagList = new ArrayList<>();
        tagList.add(Tag.EMPLOYMENT_ENTREPRENEURSHIP);
        tagList.add(Tag.ABROAD);

        List<ItemTag> findList = itemTagRepository.findByTagList(tagList);
        for (ItemTag itemTag : findList) {
            System.out.println("itemTag = " + itemTag.getItem().getTitle());
        }
    }

}