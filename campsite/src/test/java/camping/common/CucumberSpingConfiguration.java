package camping.common;


import camping.CampsiteApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { CampsiteApplication.class })
public class CucumberSpingConfiguration {
    
}
