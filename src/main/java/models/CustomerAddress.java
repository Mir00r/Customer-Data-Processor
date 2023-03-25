package models;

/**
 * @author mir00r on 23/3/23
 * @project IntelliJ IDEA
 */
public class CustomerAddress {
    private String city;
    private String cityCode;
    private String zipCode;

    private CustomerAddress(String city, String cityCode, String zipCode) {
        this.city = city;
        this.cityCode = cityCode;
        this.zipCode = zipCode;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String city;
        private String cityCode;
        private String zipCode;

        private Builder() {
        }

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Builder withCityCode(String cityCode) {
            this.cityCode = cityCode;
            return this;
        }

        public Builder withZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public CustomerAddress build() {
            return new CustomerAddress(city, cityCode, zipCode);
        }
    }

    @Override
    public String toString() {
        return "CustomerAddress{" +
                "city='" + city + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
