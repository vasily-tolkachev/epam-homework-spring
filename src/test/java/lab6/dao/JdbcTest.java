package lab6.dao;

import lab4.model.Country;
import lab4.model.SimpleCountry;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = lab6.configuration.ApplicationTestConfiguration.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = PRIVATE)
public class JdbcTest {

    final JdbcCountryDao jdbcCountryDao;

    Country australia = new SimpleCountry(1, "Australia", "AU");

    static List<Country> countryList = new ArrayList<>();

    static List<Country> countryListStartsWithA = new ArrayList<>();

    static Country countryWithChangedName = new SimpleCountry(13, "Myanmar", "MM");

    @BeforeAll
    static void beforeAll() {
        initCountryList();
    }

    @BeforeEach
    void beforeEach() {
        jdbcCountryDao.loadCountries();
    }

    static private void initCountryList() {
        for (int i = 0; i < JdbcCountryDao.COUNTRY_INIT_DATA.length; i++) {
            String[] countryInitData = JdbcCountryDao.COUNTRY_INIT_DATA[i];
            Country country = new SimpleCountry(i + 1, countryInitData[0], countryInitData[1]);
            countryList.add(country);
            if (country.getName().startsWith("A")) {
                countryListStartsWithA.add(country);
            }
        }
    }

    @Test
    @DisplayName("Get country list")
    @DirtiesContext
    void testGetCountyList() {
        List<Country> resultList = jdbcCountryDao.getAllCountries();
        System.out.println(resultList);
        assertNotNull(resultList);
        assertEquals(countryList.size(), resultList.size());
        for (int i = 0; i < countryList.size(); i++)
            assertEquals(countryList.get(i), resultList.get(i));
    }

    @Test
    @DisplayName("Getting country by name")
    @DirtiesContext
    void testGetCountryByName() {
        Country country = jdbcCountryDao.getCountryByName("Australia");
        assertNotNull(country);
        assertEquals(country, australia);
    }

    @Test
    @DisplayName("Getting country by code name")
    @DirtiesContext
    void testGetCountryByCodeName() {
        Country country = jdbcCountryDao.getCountryByCodeName("AU");
        assertNotNull(country);
        assertEquals(country, australia);
    }

    @Test
    @DisplayName("Updating country name")
    @DirtiesContext
    void testUpdateCountryName() {
        jdbcCountryDao.updateCountryName("MM", "Myanmar");
        assertEquals(jdbcCountryDao.getCountryByCodeName("MM"), countryWithChangedName);
    }

    @Test
    @DisplayName("Getting country list starts with A")
    @DirtiesContext
    void testCountryListStartsWithA() {
        List<Country> resultList = jdbcCountryDao.getCountryListStartsWith("A");
        assertNotNull(countryList);
        assertEquals(countryListStartsWithA.size(), resultList.size());
        for (int i = 0; i < countryListStartsWithA.size(); i++) {
            assertEquals(countryListStartsWithA.get(i), resultList.get(i));
        }
    }
}
