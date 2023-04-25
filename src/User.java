public class User {
    private final String name;
    private final String surname1;
    private final String surname2;
    private final String gender;
    private final String registration;
    private final long buyCounts;
    private final String email;

    public User(String name, String surname1, String surname2, String gender, String registration, String buyCounts, String email, int camelTag) {
        this.name = clearStr(name, camelTag);
        this.surname1 = clearStr(surname1, camelTag);
        this.surname2 = clearStr(surname2, camelTag);
        this.gender = clearGender(gender);
        this.registration = clearRegistration(registration);
        this.buyCounts = clearBuyCounts(buyCounts);
        this.email = email;
    }

    public String clearStr(String str, int camelTag) {
        str = str.replaceAll("[^A-Za-z\\p{L}0-9]", "")
                .replaceAll("C", "С")
                .replaceAll("P", "Р")
                .replaceAll("H", "Н")
                .replaceAll("c", "с")
                .replaceAll("p", "р");
        str = str.toUpperCase().charAt(0) + str.toLowerCase().substring(1);
        if (camelTag == 1) {
            str = camelCase(str);
        }
        return str;
    }

    public String camelCase(String str) {
        for (int i = 2; i < str.length(); i++) {
            if (i % 2 == 0) {
                str = str.substring(0, i) + str.substring(i, i + 1).toUpperCase() + str.substring(i + 1);
            }
        }
        return str;
    }

    public String clearGender(String str) {
        if (str.equals("m") || str.equals("м")) {
            return "мужской";
        } else if (str.equals("f") || str.equals("ж")) {
            return "женский";
        }
        return str;
    }

    public String clearRegistration(String str) {
        if (str.indexOf("-") == 2) {
            str = "20" + str;
        }
        return str;
    }

    public long clearBuyCounts(String str) {
        float res = Float.parseFloat(str);
        return (long) res;
    }


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

}
