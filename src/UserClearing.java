public class UserClearing {
    public static String clearStr(String str) {
        str = str.replaceAll("[^A-Za-z\\p{L}0-9]", "")
                .replaceAll("C", "С")
                .replaceAll("P", "Р")
                .replaceAll("H", "Н")
                .replaceAll("c", "с")
                .replaceAll("p", "р");
        str = str.toUpperCase().charAt(0) + str.toLowerCase().substring(1);
       /* if (camelTag == 1) {
            str = camelCase(str);
        }*/
        return str;
    }

    public static String camelCase(String str) {

        for (int i = 2; i < str.length(); i++) {
            if (i % 2 == 0) {
                str = str.substring(0, i) + str.substring(i, i + 1).toUpperCase() + str.substring(i + 1);
            }
        }
        return str;
    }

    public static String clearGender(String str) {
        if (str.equals("m") || str.equals("м")) {
            return "мужской";
        } else if (str.equals("f") || str.equals("ж")) {
            return "женский";
        }
        return str;
    }

    public static String clearRegistration(String str) {
        if (str.indexOf("-") == 2) {
            str = "20" + str;
        }
        return str;
    }

    public static long clearBuyCounts(String str) {
        float res = Float.parseFloat(str);
        return (long) res;
    }
}
