package lab7.dao.jpa;

import lab4.model.Country;
import lab4.model.SimpleCountry;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("countryJpaDao")
public class CountryJpaDaoImpl extends AbstractJpaDao {

    public static final String[][] COUNTRY_INIT_DATA = {
            {"Australia", "AU"},
            {"Canada", "CA"},
            {"France", "FR"},
            {"Hong Kong", "HK"},
            {"Iceland", "IC"},
            {"Japan", "JP"},
            {"Nepal", "NP"},
            {"Russian Federation", "RU"},
            {"Sweden", "SE"},
            {"Switzerland", "CH"},
            {"United Kingdom", "GB"},
            {"United States", "US"},
            {"Burma", "MM"},
            {"Argentina", "AR"}
    };

    public void loadCountries() {
        for (String[] countryData : COUNTRY_INIT_DATA) {
            addCountry(SimpleCountry.builder().name(countryData[0]).codeName(countryData[1]).build());
        }
    }

    @Override
    public void addCountry(Country country) {
        withEntityManager(entityManager -> entityManager.merge(country));
    }

    @Override
    public List<Country> getAllCountries() {
        return mapEntityManager(entityManager ->
                entityManager.createQuery("select c from SimpleCountry c", Country.class)
                        .getResultList());
    }

    @Override
    public Country getCountryByName(String name) {
        return mapEntityManager(entityManager ->
                entityManager.createQuery(
                        "select c from SimpleCountry c where c.name = :name",
                        Country.class)
                .setParameter("name", name)
                .getSingleResult()
        );
    }

}