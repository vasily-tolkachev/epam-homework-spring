package lab5.model;

import lab4.model.Person;
import org.springframework.stereotype.Component;

@Component("bar")
public class ApuBar implements Bar {
    @Override
    public Drink sellSquishee(Person person) {
        if (person.isBroke()) {
            throw new CustomerBrokenException();
        }
        System.out.println("There you are!");
        return () -> "Squishee";
    }

}
