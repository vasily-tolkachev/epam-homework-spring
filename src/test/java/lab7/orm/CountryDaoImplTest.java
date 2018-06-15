package lab7.orm;

import lab4.model.Country;
import lab4.model.SimpleCountry;
import lab7.dao.jpa.CountryJpaDaoImpl;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIn.in;
import static org.hamcrest.core.Is.is;
/**
 * Illustrates basic use of Hibernate as a JPA provider.
 */
@FieldDefaults(level = PRIVATE, makeFinal = true)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = lab7.configuration.ApplicationTestConfiguration.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class CountryDaoImplTest {

    @Autowired
    private CountryJpaDaoImpl countryJpaDao;

    @Test
    @DisplayName("Test save country")
    void testSaveCountry() {
        // given
        Country exampleCountry = new SimpleCountry("Australia", "AU");
        int initialSize = countryJpaDao.getAllCountries().size();

        // when
        countryJpaDao.addCountry(exampleCountry);

        // then
        List<Country> countryList = countryJpaDao.getAllCountries();
        assertThat(countryList.size(), is(initialSize + 1));
        assertThat(exampleCountry, is(in(countryList)));
    }

    @Test
    @DisplayName("Test get all countries")
    void testGetAllCountries() {
        // given
        int initialSize = countryJpaDao.getAllCountries().size();

        // when
        countryJpaDao.addCountry(new SimpleCountry("Canada", "CA"));

        // then
        List<Country> countryList = countryJpaDao.getAllCountries();
        assertThat(countryList.size(), is(initialSize + 1));
    }

    @Test
    @DisplayName("Test get country by name")
    void testGetCountryByName() {
        // given
        Country exampleCountry = new SimpleCountry("Australia", "AU");
        countryJpaDao.addCountry(exampleCountry);

        // when
        Country resultCountry = countryJpaDao.getCountryByName("Australia");

        // then
        assertThat(exampleCountry, is(resultCountry));
    }

}