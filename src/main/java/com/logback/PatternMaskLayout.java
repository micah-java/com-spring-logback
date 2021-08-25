package com.logback;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Layout;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMaskLayout extends PatternLayout {

    private final static String MASK_REPLACE = "*疑似敏感信息*";

    public void addMaskKey(String maskKey) {
        System.out.println("maskKey: " + maskKey);
        MaskUtil.maskPatterns.add(maskKey.concat("=(.*?)[,}]"));
        MaskUtil.maskPatterns.add("\"".concat(maskKey).concat("\"").concat(":(.*?)[,}]"));
    }

    @Override
    public String doLayout(ILoggingEvent event) {
        return maskMessage(super.doLayout(event));
    }

    private String maskMessage(String msg){

        Pattern pattern = MaskUtil.getMultilinePattern();
        Matcher matcher = pattern.matcher(msg);
        Set<String> sensitiveMessage = new HashSet<>();
        while (matcher.find()){
            sensitiveMessage.add(matcher.group(0));
        }
        if(sensitiveMessage.size() > 0){
            for (String s : sensitiveMessage) {
                if(StringUtils.isNoneBlank(s)){
                    String[] split = s.split(":|=");
                    String replacement = MASK_REPLACE;
                    if(split.length >= 2){
                        replacement = split[0].concat("=").concat(MASK_REPLACE).concat(",");
                    }
                    msg = msg.replace(s,replacement);
                }
            }
        }
        return msg;
    }





























}
