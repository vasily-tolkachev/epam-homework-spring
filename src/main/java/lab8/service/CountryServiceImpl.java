package lab8.service;


import lab4.model.Country;
import lab6.dao.CountryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryDao countryDao;

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

    public List<Country> getAllCountriesRequired() {
        return this.countryDao.getAllCountries();
    }

    public List<Country> getAllCountriesRequiresNew() {
        return this.countryDao.getAllCountries();
    }

    public List<Country> getAllCountriesSupports() {
        return this.countryDao.getAllCountries();
    }

    public List<Country> getAllCountriesNever() {
        return this.countryDao.getAllCountries();
    }

    public List<Country> getAllCountriesMandatory() {
        return this.countryDao.getAllCountries();
    }

    public List<Country> getAllCountriesNotSupported() {
        return this.countryDao.getAllCountries();
    }

    public List<Country> getAllCountries() {
        return this.countryDao.getAllCountries();
    }

    public CountryDao getCountryDao() {
        return this.countryDao;
    }

    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }
}

