package lab8;


import lab4.model.Country;
import lab6.dao.CountryDao;
import lab6.dao.JdbcCountryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CountryServiceImpl implements CountryService {
    @Autowired
    private JdbcCountryDao jdbcCountryDao;

    public CountryServiceImpl() {
    }

    public List<Country> getAllCountriesInsideTransaction(Propagation propagation) {
        if (Propagation.REQUIRED.equals(propagation)) {
            return this.getAllCountriesRequired();
        } else if (Propagation.REQUIRES_NEW.equals(propagation)) {
            return this.getAllCountriesRequiresNew();
        } else if (Propagation.SUPPORTS.equals(propagation)) {
            return this.getAllCountriesSupports();
        } else if (Propagation.NEVER.equals(propagation)) {
            return this.getAllCountriesNever();
        } else if (Propagation.MANDATORY.equals(propagation)) {
            return this.getAllCountriesMandatory();
        } else {
            return Propagation.NOT_SUPPORTED.equals(propagation) ? this.getAllCountriesNotSupported() : this.getAllCountries();
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<Country> getAllCountriesRequired() {
        return this.jdbcCountryDao.getAllCountries();
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public List<Country> getAllCountriesRequiresNew() {
        return this.jdbcCountryDao.getAllCountries();
    }

    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public List<Country> getAllCountriesSupports() {
        return this.jdbcCountryDao.getAllCountries();
    }

    @Transactional(readOnly = false, propagation = Propagation.NEVER)
    public List<Country> getAllCountriesNever() {
        return this.jdbcCountryDao.getAllCountries();
    }

    @Transactional(readOnly = false, propagation = Propagation.MANDATORY)
    public List<Country> getAllCountriesMandatory() {
        return this.jdbcCountryDao.getAllCountries();
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<Country> getAllCountriesNotSupported() {
        return this.jdbcCountryDao.getAllCountries();
    }

    public List<Country> getAllCountries() {
        return this.jdbcCountryDao.getAllCountries();
    }

    public CountryDao getCountryDao() {
        return this.jdbcCountryDao;
    }

    public void setCountryDao(JdbcCountryDao jdbcCountryDao) {
        this.jdbcCountryDao = jdbcCountryDao;
    }
}