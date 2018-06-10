package lab6.dao;

import lab4.model.Country;

import java.util.List;

public interface CountryDao {
    List<Country> getAllCountries();
    Country getCountryByName(String name);
}