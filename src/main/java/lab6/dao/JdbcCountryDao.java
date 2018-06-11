package lab6.dao;

import lab4.model.Country;
import lab4.model.SimpleCountry;
import lab6.dao.utils.Utils;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
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
            {"Burma", "MM"}
    };

    static String LOAD_COUNTRIES_SQL = "insert into country (name, code_name) values ('%s', '%s')";
    static String GET_ALL_COUNTRIES_SQL = "select * from country";
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
            getJdbcTemplate().execute(
                    String.format(LOAD_COUNTRIES_SQL, countryData[0], countryData[1]));
        }
    }

    @Override
    public List<Country> getAllCountries() {
        return getNamedParameterJdbcTemplate().query(GET_ALL_COUNTRIES_SQL, COUNTRY_ROW_MAPPER);
    }

    @Override
    public Country getCountryByName(String name) {
        return getCountryByStringParameter("name", name, GET_COUNTRY_BY_NAME_SQL);
    }

    public Country getCountryByCodeName(String codeName) {
        return getCountryByStringParameter("codeName", codeName, GET_COUNTRY_BY_CODE_NAME_SQL);
    }

    private Country getCountryByStringParameter(String parameterName, String parameterValue, String sql) {
        Country country = getNamedParameterJdbcTemplate().queryForObject(
                sql,
                new MapSqlParameterSource(parameterName, parameterValue),
                COUNTRY_ROW_MAPPER
        );
        if (country == null) {
            throw new CountryNotFoundException();
        }
        return country;
    }

    public void updateCountryName(String codeName, String newName) {
        getNamedParameterJdbcTemplate().update(
                UPDATE_COUNTRY_NAME_SQL,
                Utils.mapOf("codeName", codeName, "name", newName));
    }
}
