package lab6.dao;

import lab4.model.Country;
import lab4.model.SimpleCountry;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Repository
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class JdbcCountryDao extends NamedParameterJdbcDaoSupport implements CountryDao {
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

    static String ADD_COUNTRY_LIST_SQL = "insert into country (name, code_name) values (:name, :codeName)";
    static String GET_ALL_COUNTRIES_SQL = "select * from country";
    static String GET_COUNTRY_BY_NAME_PATTERN_SQL = "select * from country where name like :name";
    static String GET_COUNTRY_BY_NAME_SQL = "select * from country where name = :name";
    static String GET_COUNTRY_BY_CODE_NAME_SQL = "select * from country where code_name = :codeName";
    static String UPDATE_COUNTRY_NAME_SQL = "update country set name = :name where code_name = :codeName";

    static RowMapper<Country> COUNTRY_ROW_MAPPER = (resultSet, __) -> new SimpleCountry(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getString("code_name")
    );

    public JdbcCountryDao(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public void loadCountries() {
        for (String[] countryData : COUNTRY_INIT_DATA) {
            addCountry(SimpleCountry.builder().name(countryData[0]).codeName(countryData[1]).build());
        }
    }

    public void addCountry(Country country) {
        country.setId(addCountry(country.getName(), country.getCodeName()));
    }

    public long addCountry(String name, String codeName) {
        val keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("codeName", codeName)
                .addValue("name", name);
        getNamedParameterJdbcTemplate().update(ADD_COUNTRY_LIST_SQL, mapSqlParameterSource, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public List<Country> getAllCountries() {
        return getNamedParameterJdbcTemplate().query(GET_ALL_COUNTRIES_SQL, COUNTRY_ROW_MAPPER);
    }

    @Override
    public Country getCountryByName(String name) {
        return getCountryByStringParameter("name", name, GET_COUNTRY_BY_NAME_SQL).get(0);
    }

    public Country getCountryByCodeName(String codeName) {
        return getCountryByStringParameter("codeName", codeName, GET_COUNTRY_BY_CODE_NAME_SQL).get(0);
    }

    private List<Country> getCountryByStringParameter(String parameterName, String parameterValue, String sql) {
        List<Country> countryList = getNamedParameterJdbcTemplate().query(
                sql,
                new MapSqlParameterSource(parameterName, parameterValue),
                COUNTRY_ROW_MAPPER
        );
        if (countryList.isEmpty()) {
            throw new CountryNotFoundException();
        }
        return countryList;
    }

    public void updateCountryName(String codeName, String newName) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("codeName", codeName)
                .addValue("name", newName);
        getNamedParameterJdbcTemplate().update(UPDATE_COUNTRY_NAME_SQL, mapSqlParameterSource);
    }

    public List<Country> getCountryListStartsWith(String startFragment) {
        return getCountryByStringParameter("name", startFragment + "%", GET_COUNTRY_BY_NAME_PATTERN_SQL);
    }
}
