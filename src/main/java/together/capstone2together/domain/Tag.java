package together.capstone2together.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum Tag { //17개
    PLANNING_IDEA("기획/아이디어"),
    ADVERTISING_MARKETING("광고/마케팅"),
    THESIS_REPORT("논문/리포트"),
    VIDEO_UCC_PHOTO("영상/UCC/사진"),
    DESIGN_CHARACTER_WEBTOON("디자인/캐릭터/웹툰"),
    WEB_MOBILE_IT("웹/모바일/IT"),
    GAME_SOFTWARE("게임/소프트웨어"),
    SCIENCE_ENGINEERING("과학/공학"),
    LITERATURE_WRITING_SCENARIO("문학/글/시나리오"),
    ARCHITECTURE_CONSTRUCTION_INTERIOR("건축/건설/인테리어"),
    NAMING_SLOGAN("네이밍/슬로건"),
    ART_MUSIC("예체능/미술/음악"),
    EXTRACURRICULAR_SUPPORTERS("대외활동/서포터즈"),
    VOLUNTEER("봉사활동"),
    EMPLOYMENT_ENTREPRENEURSHIP("취업/창업"),
    ABROAD("해외"),
    OTHER("기타");
    private String description;
    Tag(String description){
        this.description = description;
    }

    public static Tag findByKeyword(String keyword){
        for (Tag tag : Tag.values()) {
            if(tag.name().equalsIgnoreCase(keyword))
                return tag;
        }
        return null;
    }
    public String getDescription(Tag tag){
        return tag.description;
    }
    public static String containedKeyword(String keyword){
        for (Tag tag : Tag.values()) {
            if(tag.getDescription().contains(keyword))
                return tag.getDescription();
        }
        return null;
    }
}
