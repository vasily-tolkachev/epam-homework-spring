package lab7.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({lab7.configuration.ApplicationTestConfiguration.class})
@ComponentScan("lab7")
public class ApplicationTestConfiguration {

}
