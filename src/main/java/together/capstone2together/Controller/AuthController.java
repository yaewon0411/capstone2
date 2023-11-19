package together.capstone2together.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import together.capstone2together.domain.Member;
import together.capstone2together.domain.MemberTag;
import together.capstone2together.dto.JoinDto;
import together.capstone2together.dto.LoginDto;
import together.capstone2together.service.MemberService;
import together.capstone2together.service.MemberTagService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;
    private final MemberTagService memberTagService;

    @PostMapping("/join") //회원가입
    public ResponseEntity<String> join(@RequestBody JoinDto dto) {
        Member member = new Member(dto.getId(), dto.getPassword(), dto.getName(), dto.getKakaotalkId(), dto.getTagList());
        memberService.join(member);

        System.out.println("member.getTagList() = " + member.getTagList());
        
        List<MemberTag> memberTagList = MemberTag.create(member);
        memberTagService.save(memberTagList);

        return ResponseEntity.ok("join success");
    }
    @PostMapping("/login") //로그인
    public ResponseEntity<String> login(@RequestBody LoginDto dto){
        memberService.login(dto.getId(), dto.getPassword());
        return ResponseEntity.ok("login success");
    }

}
