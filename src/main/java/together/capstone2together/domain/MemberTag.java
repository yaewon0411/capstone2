package together.capstone2together.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity@Getter@Setter
public class MemberTag {
    @Id @GeneratedValue
    @Column(name = "memberTag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private Tag tag;

    public static List<MemberTag> create(Member member){
        List<MemberTag> memberTagList = new ArrayList<>();
        List<Tag> tagList = member.getTagList();
        for (Tag tag : tagList) {
            MemberTag memberTag = new MemberTag();
            memberTag.setTag(tag);
            memberTag.setMember(member);
            memberTagList.add(memberTag);
        }
        return memberTagList;
    }
}
