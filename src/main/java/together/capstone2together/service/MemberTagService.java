package together.capstone2together.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import together.capstone2together.domain.Member;
import together.capstone2together.domain.MemberTag;
import together.capstone2together.domain.Tag;
import together.capstone2together.dto.TagListDto;
import together.capstone2together.repository.MemberTagRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberTagService {

    private final MemberTagRepository memberTagRepository;

    public void save(List<MemberTag> memberTagList){
        memberTagRepository.save(memberTagList);
    }
    //사용자 태그 재설정
    public void changeTags(Member member, TagListDto dto){
        List<MemberTag> findList = memberTagRepository.findByMember(member);
        List<Tag> changeList = dto.getTagList();
        for (MemberTag memberTag : findList) {
            memberTagRepository.delete(memberTag);
        }
        member.setTagList(changeList);
        List<MemberTag> memberTagList = MemberTag.create(member);
        memberTagRepository.update(memberTagList);
    }
}
