package com.logback;

import com.google.common.base.CharMatcher;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MaskUtil {

    public static final List<String> maskPatterns = new ArrayList<>();
    public static final List<String> defaultMaskKey = new ArrayList<>();

    static {
        //手机
        //maskPatterns.add("(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}");
        //邮箱
        //maskPatterns.add("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        //手机号
        //defaultMaskKey.add("mobileNo");
        //defaultMaskKey.add("email");
        defaultMaskKey.add("name");
        defaultMaskKey.add("age");

        for (String maskKey : defaultMaskKey) {
            maskPatterns.add(maskKey.concat("=(.*?)[,}]"));
            maskPatterns.add("\"".concat(maskKey).concat("\"").concat(":(.*?)[,}]"));
        }
    }

    public static Pattern getMultilinePattern(){
        return Pattern.compile(String.join("|",maskPatterns),Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
    }
}
