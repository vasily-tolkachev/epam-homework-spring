package lab8.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;

@Configuration
@Import({lab7.configuration.ApplicationTestConfiguration.class})
@ComponentScan("lab8")
@EnableLoadTimeWeaving(aspectjWeaving = EnableLoadTimeWeaving.AspectJWeaving.ENABLED)
public class ApplicationTestConfiguration {
    @Autowired LocalContainerEntityManagerFactoryBean entityManagerFactoryBean;

    @Bean
    public JpaTransactionManager txManager() {
        return new JpaTransactionManager((EntityManagerFactory) entityManagerFactoryBean);
    }

}
