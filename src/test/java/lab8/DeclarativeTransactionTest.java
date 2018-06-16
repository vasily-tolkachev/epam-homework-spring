package lab8;

import lab8.service.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.annotation.Propagation;

import static lombok.AccessLevel.PRIVATE;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = lab8.configuration.ApplicationTestConfiguration.class)
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeclarativeTransactionTest {

    @Autowired
    private CountryService countryService;

    @Test
    @DirtiesContext
    void testRequiredPropagationInsideTransaction() {
        assertNotNull(countryService.getAllCountriesInsideTransaction(Propagation.REQUIRED));
    }

    @Test
    @DirtiesContext
    void testRequiredPropagationWithoutTransaction() {
        assertNotNull(countryService.getAllCountriesRequired());
    }

    @Test
    @DirtiesContext
    void testMandatoryPropagationWithoutTransaction() {
        try {
            countryService.getAllCountriesMandatory();
        } catch (Exception e) {
            assertTrue(e instanceof IllegalTransactionStateException);
            e.printStackTrace();
        }
    }

    @Test
    @DirtiesContext
    void testMandatoryPropagationInsideTransaction() {
        assertNotNull(countryService.getAllCountriesInsideTransaction(Propagation.MANDATORY));
    }
}
