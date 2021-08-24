package com.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackApplication {

    private static final  Logger logger = LoggerFactory.getLogger(LogbackApplication.class);

    public static void main(String[] args) {
        for (int i = 0; i < 1 ; i++) {
            User user1 = new User("18503084321", 12);
            logger.info("user1: " + user1);
            logger.info("{\"age\":18,\"username2\":\"ll\"}");
        }
    }
}
