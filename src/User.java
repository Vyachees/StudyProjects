public class User {
    private final String name;
    private final String surname1;
    private final String surname2;
    private final String gender;
    private final String registration;
    private final long buyCounts;
    private final String email;

    public String getName() {
        return name;
    }

    public String getSurname1() {
        return surname1;
    }

    public String getSurname2() {
        return surname2;
    }

    public String getGender() {
        return gender;
    }

    public String getRegistration() {
        return registration;
    }

    public Long getBuyCounts() {
        return buyCounts;
    }

    public String getEmail() {
        return email;
    }

    public static class Builder {
        private final String name;
        private final String surname1;
        private final String surname2;
        private final String gender;
        private final String registration;
        private final long buyCounts;
        private final String email;
        private int camelTag = 0;

        public Builder(String name, String surname1, String surname2, String gender, String registration, String buyCounts, String email) {
            this.name = UserClearing.clearStr(name);
            this.surname1 = UserClearing.clearStr(surname1);
            this.surname2 = UserClearing.clearStr(surname2);
            this.gender = UserClearing.clearGender(gender);
            this.registration = UserClearing.clearRegistration(registration);
            this.buyCounts = UserClearing.clearBuyCounts(buyCounts);
            this.email = email;
        }

        public Builder setCamelTag(int camel) {
            camelTag = camel;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }

    public User(Builder builder) {
        if (builder.camelTag == 1) {
            this.name = UserClearing.camelCase(builder.name);
            this.surname1 = UserClearing.camelCase(builder.surname1);
            this.surname2 = UserClearing.camelCase(builder.surname2);
        } else {
            this.name = builder.name;
            this.surname1 = builder.surname1;
            this.surname2 = builder.surname2;
        }
        this.gender = builder.gender;
        this.registration = builder.registration;
        this.buyCounts = builder.buyCounts;
        this.email = builder.email;

    }

}
