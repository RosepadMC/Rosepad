package net.minecraft.src;

public class Util {
    private Util() {}

    public static String filterWith(String str, String filter) {
        StringBuilder out = new StringBuilder();

        for (Character c : str.toCharArray()) {
            if (filter.contains(c.toString())) out.append(c);
        }

        return out.toString();
    }

    public static String reverse(String str) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            out.append(str.charAt(str.length() - 1 - i));
        }
        return out.toString();
    }

    public static String pad(String str, int len) {
        if (len == 0) return str;
        if (str.length() == len) return str;
        return "0" + pad(str, len - 1);
    }
}
