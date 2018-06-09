package lab5.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@Import(lab4.configuration.ApplicationTestConfiguration.class)
@ComponentScan("lab5")
@EnableAspectJAutoProxy
public class ApplicationTestConfiguration {

}