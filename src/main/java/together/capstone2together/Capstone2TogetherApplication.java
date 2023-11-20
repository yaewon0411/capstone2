package together.capstone2together;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import together.capstone2together.domain.SurveyAnswer;
import together.capstone2together.domain.Tag;
import together.capstone2together.service.SurveyAnswerService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@SpringBootApplication
public class Capstone2TogetherApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Capstone2TogetherApplication.class, args);

	}


}


