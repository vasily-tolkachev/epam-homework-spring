package lab6.dao;

import lab4.model.Country;

import java.util.List;

public interface CountryDao {
    void addCountry(Country country);

    List<Country> getAllCountries();

    Country getCountryByName(String name);
}