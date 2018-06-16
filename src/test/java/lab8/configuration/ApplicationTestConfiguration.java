package lab8.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.Import;

@Configuration
@Import({lab7.configuration.ApplicationTestConfiguration.class})
@ComponentScan("lab8")
@EnableLoadTimeWeaving(aspectjWeaving = EnableLoadTimeWeaving.AspectJWeaving.ENABLED)
public class ApplicationTestConfiguration {

}
