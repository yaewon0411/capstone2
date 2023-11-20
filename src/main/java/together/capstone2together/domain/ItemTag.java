package together.capstone2together.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "ItemTag")
public class ItemTag implements Serializable {
    @Id @GeneratedValue
    @Column(name = "itemTag_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    private Tag tag;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="item_id")
    private Item item;

    public static List<ItemTag> create(Item item){
        List<ItemTag> itemTags = new ArrayList<>();
        List<Tag> findList = item.getTagList();
        for (Tag tag : findList) {
            ItemTag itemTag = new ItemTag();
            itemTag.setTag(tag);
            itemTag.setItem(item);
            itemTags.add(itemTag);
        }
        return itemTags;
    }
}
