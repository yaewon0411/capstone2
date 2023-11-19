package together.capstone2together.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class QuestionTest {

    @Test
    void jsonNode를question으로풀기테스트() throws JsonProcessingException {
        String jsonString = "{\n" +
                "  \"Question\" : {\n" +
                "    \"1\" : \"이름은?\",\n" +
                "    \"2\" : \"나이는?\"\n" +
                "  },\n" +
                "  \"Room\" : {\n" +
                "    \"title\" : \"방 제목\",\n" +
                "    \"content\" : \"방 소개글\",\n" +
                "    \"city\" : \"제주\",\n" +
                "    \"capacity\" : \"2명\",\n" +
                "    \"memberId\" : \"팀장 아이디\",\n" +
                "    \"itemId\" : \"아이템 아이디\"\n" +
                "  }\n" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        JsonNode node = jsonNode.get("Question");
        System.out.println("node = " + node);
        List<String> questionList = new ArrayList<>();
        for (JsonNode node1 : node) {
            questionList.add(node1.textValue());
        }

        System.out.println("questionList = " + questionList);
    }
}
