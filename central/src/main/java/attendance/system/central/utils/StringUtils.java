package attendance.system.central.utils;

import java.util.List;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

public class StringUtils {

    public static String Curly(String string) {
        return "{" + string + "}";
    }

    public static String hyphenate(List<String> strings) {
        StringBuilder hyphenatedString = new StringBuilder();
        for (int i = 0; i < strings.size() - 1; i++) hyphenatedString.append(strings.get(i)).append("-");
        hyphenatedString.append(strings.get(strings.size() - 1));
        return hyphenatedString.toString();
    }
}
