package lab4.model;

import org.springframework.stereotype.Component;

@Component("country")
public class SimpleCountry implements Country {
    private long id;
    private final String name;
    private final String codeName;

    public static SimpleCountry.SimpleCountryBuilder builder() {
        return new SimpleCountry.SimpleCountryBuilder();
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getCodeName() {
        return this.codeName;
    }

    public String toString() {
        return "SimpleCountry(name=" + this.getName() + ", codeName=" + this.getCodeName() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleCountry that = (SimpleCountry) o;

        return (name != null ? name.equals(that.name) : that.name == null) && (codeName != null ? codeName.equals(that.codeName) : that.codeName == null);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (codeName != null ? codeName.hashCode() : 0);
        return result;
    }

    public SimpleCountry(long id, String name, String codeName) {
        this.id = id;
        this.name = name;
        this.codeName = codeName;
    }

    /* public SimpleCountry(String name, String codeName) {
         this(0L, name, codeName);
     }*/
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
