package together.capstone2together.service;


import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import together.capstone2together.domain.Item;
import together.capstone2together.domain.ItemTag;
import together.capstone2together.domain.Tag;
import together.capstone2together.dto.SearchDto;
import together.capstone2together.repository.ItemTagRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemTagService {

    private final ItemTagRepository itemTagRepository;
    private final SubService subService;

    public void save(List<ItemTag> itemTagList){
        itemTagRepository.save(itemTagList);
    }
    public JSONArray findItemByInterestedTag(List<Tag> taglist){
        System.out.println("==========================================");
        System.out.println("taglist = " + taglist);
        System.out.println("==========================================");
        List<ItemTag> findList = itemTagRepository.findByTagList(taglist);
        JSONArray array = new JSONArray();
        for (ItemTag itemTag : findList) {
            JSONObject object = new JSONObject();
            array.add(makeObject(itemTag, object));
        }
        return array;
    }

    private Object makeObject(ItemTag itemTag, JSONObject object) {
        Item findOne = itemTag.getItem();
        object.put("title", findOne.getTitle());
        object.put("itemId",findOne.getId());
        object.put("sponsor",findOne.getSponsor());
        object.put("views",findOne.getViews());
        object.put("img",findOne.getImg());
        object.put("Dday",subService.makeDday(findOne.getDeadline()));
        return object;
    }

    public List<SearchDto> searchItems(String keyword) {
        Tag tag = Tag.findByKeyword(keyword);
        if(tag==null){
            tag = Tag.containedKeyword(keyword);
        }
        System.out.println("tag = " + tag);
        List<ItemTag> findList = itemTagRepository.findByTag(tag);
        //아이디랑 제목만 내보내면됨
        List<SearchDto> searchList = new ArrayList<>();
        for (ItemTag itemTag : findList) {
            SearchDto dto = new SearchDto();
            dto.setId(itemTag.getItem().getId());
            dto.setTitle(itemTag.getItem().getTitle());
            dto.setTagList(itemTag.getItem().getTagList());
            searchList.add(dto);
        }
        return searchList;
    }
}
