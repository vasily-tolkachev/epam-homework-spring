package lab7.dao.jpa;

import lab4.model.Country;
import lab6.dao.CountryDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CountryJpaDaoImpl extends AbstractJpaDao implements CountryDao {

    @Override
    public void addCountry(Country country) {
//		TODO: Implement it
        EntityManager em = null;

        if (em != null) {
            em.close();
        }
    }

    @Override
    public List<Country> getAllCountries() {
//	TODO: Implement it
        return null;
    }// getAllcountries()

    @Override
    public Country getCountryByName(String name) {
//		TODO: Implement it

        return null;
    }

}