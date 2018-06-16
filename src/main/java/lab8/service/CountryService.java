package lab8.service;

import lab4.model.Country;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;

public interface CountryService {
    List<Country> getAllCountriesInsideTransaction(Propagation var1);

    List<Country> getAllCountriesRequired();

    List<Country> getAllCountriesRequiresNew();

    List<Country> getAllCountriesSupports();

    List<Country> getAllCountriesNever();

    List<Country> getAllCountriesMandatory();

    List<Country> getAllCountriesNotSupported();

    List<Country> getAllCountries();
}
