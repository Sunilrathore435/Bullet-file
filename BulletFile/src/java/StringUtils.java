package java;


public class StringUtils {

    public static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static void printAllSubstrings(String str) {
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j <= str.length(); j++) {
                System.out.println(str.substring(i, j));
            }
        }
    }
}
