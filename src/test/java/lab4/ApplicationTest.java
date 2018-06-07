package lab4;

import lab4.configuration.ApplicationTestConfiguration;
import lab4.model.Person;
import lab4.model.SimpleContact;
import lab4.model.SimpleCountry;
import lab4.model.SimplePerson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationTest {

    static BeanFactory BEAN_FACTORY = new AnnotationConfigApplicationContext(ApplicationTestConfiguration.class);

    @Test
    void test() {
        assertEquals(getExpectedPerson(), BEAN_FACTORY.getBean("person"));
    }

    static Person getExpectedPerson() {
        return SimplePerson.builder()
                .id(1L)
                .age(35)
                .height(1.78F)
                .isProgrammer(true)
                .firstName("John")
                .lastName("Smith")
                .country(SimpleCountry.builder().id(1L).name("Russia").codeName("RU").build())
                .contact(SimpleContact.builder().id(1L).value("asd@asd.ru").build())
                .contact(SimpleContact.builder().id(1L).type("TELEPHONE").value("+55 11 99999-5555").build())
                .build();
    }
}
