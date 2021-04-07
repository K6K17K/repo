package utils;

import java.util.regex.Pattern;

/**
 * @Time : 2020/8/7 12:48
 * @Author : KKK
 * @File : IsNumUtils.java
 * @Software: IntelliJ IDEA
 **/
public class IsNumUtils {
    public static boolean isNum(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
}
