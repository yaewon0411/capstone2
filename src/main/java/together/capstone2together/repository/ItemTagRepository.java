package together.capstone2together.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import together.capstone2together.domain.ItemTag;
import together.capstone2together.domain.Tag;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemTagRepository {
    private final EntityManager em;

    public void save(List<ItemTag> itemTagList){
        for (ItemTag itemTag : itemTagList) {
            validateDuplicatedItemTag(itemTag);
            em.persist(itemTag);
        }
    }
    private void validateDuplicatedItemTag(ItemTag itemTag) {
        List <ItemTag> findList = em.createQuery("select it from ItemTag it where it.item = :item and it.tag = :tag", ItemTag.class)
                .setParameter("item", itemTag.getItem())
                .setParameter("tag", itemTag.getTag())
                .getResultList();
        if(findList.size()>0) throw new IllegalStateException("중복 레코드");
    }
    //사용자 관심 태그로 아이템 조회 -> 20개 출력
    /*
    태그 개수에 따라서 내보낼 아이템 결정해야 함
    태그 개수 5개면 각 태그당 4개씩 내보내는 식으로
     */
    public List<ItemTag> findByTag(List<Tag> tagList){
        if(tagList.size()==0) throw new IllegalStateException("설정한 관심 태그가 없습니다.");
        List<ItemTag> result = new ArrayList<>();
        int limit = 20;
        int size = tagList.size();
        int perSize = limit/size;

        for (Tag tag : tagList) {
            List<ItemTag> subList = em.createQuery("" +
                            "select it from ItemTag it " +
                            "left join fetch it.item i where it.tag = :tag and i.available = :Y", ItemTag.class)
                    .setParameter("tag", tag)
                    .setParameter("Y","Y")
                    .setFirstResult(0)
                    .setMaxResults(perSize)
                    .getResultList();

            if(result.size()==limit) return result;
            result.addAll(subList);
        }
        if(result.size()<limit){
            int offset = limit - result.size();
            Tag[] tags = Tag.values();
            int random = (int)(Math.random() *16) + 1;
            Tag addtag = tags[random];
            List<ItemTag> addList = em.createQuery("" +
                            "select it from ItemTag it " +
                            "left join fetch it.item i where it.tag = :tag and i.available = :Y", ItemTag.class)
                    .setParameter("tag", addtag)
                    .setParameter("Y","Y")
                    .setFirstResult(offset)
                    .setMaxResults(limit - offset) //테스트하면서 조절
                    .getResultList();

            result.addAll(addList);
            return result;
        }
        return result;
    }
}
