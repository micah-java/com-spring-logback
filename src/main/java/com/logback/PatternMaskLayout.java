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

    private String maskKey;

    public String getMaskKey() {
        return maskKey;
    }

    public void setMaskKey(String maskKey) {
        this.maskKey = maskKey;
    }

    @Override
    public String doLayout(ILoggingEvent event) {

        System.out.println("maskKey: "  + maskKey);

        return maskMessage(super.doLayout(event),maskKey);
    }

    private String maskMessage(String msg,String maskKey){

        Pattern pattern = MaskUtil.getMultilinePattern(maskKey);
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
