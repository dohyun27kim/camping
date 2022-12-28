package camping.common;


import camping.MileageApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { MileageApplication.class })
public class CucumberSpingConfiguration {
    
}
