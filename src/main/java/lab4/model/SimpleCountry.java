package lab4.model;

import org.springframework.stereotype.Component;

@Component("country")
public class SimpleCountry implements Country {
    private long id;
    private String name;
    private String codeName;

    public SimpleCountry(String name, String codeName) {
        this(0L, name, codeName);
    }

    public SimpleCountry setId(long id) {
        this.id = id;
        return this;
    }

    public SimpleCountry setName(String name) {
        this.name = name;
        return this;
    }

    public SimpleCountry setCodeName(String codeName) {
        this.codeName = codeName;
        return this;
    }

    public static SimpleCountry.SimpleCountryBuilder builder() {
        return new SimpleCountry.SimpleCountryBuilder();
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCodeName() {
        return this.codeName;
    }

    public String toString() {
        return "SimpleCountry(id=" + this.getId() + ", name=" + this.getName() + ", codeName=" + this.getCodeName() + ")";
    }

    public SimpleCountry() {
    }

    public SimpleCountry(long id, String name, String codeName) {
        this.id = id;
        this.name = name;
        this.codeName = codeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleCountry that = (SimpleCountry) o;

        if (!name.equals(that.name)) return false;
        return codeName.equals(that.codeName);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + codeName.hashCode();
        return result;
    }

    public static class SimpleCountryBuilder {
        private long id;
        private String name;
        private String codeName;

        SimpleCountryBuilder() {
        }

        public SimpleCountry.SimpleCountryBuilder id(long id) {
            this.id = id;
            return this;
        }

        public SimpleCountry.SimpleCountryBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SimpleCountry.SimpleCountryBuilder codeName(String codeName) {
            this.codeName = codeName;
            return this;
        }

        public SimpleCountry build() {
            return new SimpleCountry(this.id, this.name, this.codeName);
        }

        public String toString() {
            return "SimpleCountry.SimpleCountryBuilder(id=" + this.id + ", name=" + this.name + ", codeName=" + this.codeName + ")";
        }
    }
}

