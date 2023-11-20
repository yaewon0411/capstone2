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
import together.capstone2together.repository.ItemRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {
    private final ItemRepository itemRepository;
    private final SubService subService;

    //실시간 인기 활동
    public JSONArray getTop20Views(){
        List<Item> findList = itemRepository.findTop20ByViews(getCurrentTime());
        JSONArray array = new JSONArray();
        for (Item item : findList) {
            JSONObject object = new JSONObject();
            array.add(subService.makeObject(item, object));
        }
        return array;
    }
    public Item findById(Long id){
        return itemRepository.findById(id).get();
    }

    public JSONObject showItemInfo(Long id){
        Item findOne = itemRepository.findById(id).get();
        return subService.makeItemJson(findOne);
    }

    //마감 직전 활동
    public JSONArray getImminentDeadline(){
        List<Item> findList = itemRepository.findTop20ByDeadlineAfterOrderByDeadlineAsc(getCurrentTime());
        JSONArray array = new JSONArray();
        for (Item item : findList) {
            JSONObject object = new JSONObject();
            array.add(subService.makeObject(item, object));
        }
        return array;
    }
    //최근 추가된 활동
    public JSONArray getRecentlyAddedItem(){
        List<Item> findList = itemRepository.findTop20ByOrderByIdDesc(getCurrentTime());
        JSONArray array = new JSONArray();
        for (Item item : findList) {
            JSONObject object = new JSONObject();
            array.add(subService.makeObject(item, object));
        }
        return array;
    }
    private static String getCurrentTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return currentTime.format(formatter);
    }
    public List<SearchDto> searchItems(String keyword) { //키워드는 제목에 들어있을 수도, 상세 내용에 들어있을 수도, 태그가 될 수도 있음
        List<Item> findList = itemRepository.searchedItem(keyword);
        List<SearchDto> searchList = new ArrayList<>();
        for (Item item : findList) {
            SearchDto dto = new SearchDto();
            dto.setId(item.getId());
            dto.setTitle(item.getTitle());
            dto.setTagList(item.getTagList());
            searchList.add(dto);
        }
        return searchList;
    }

}
