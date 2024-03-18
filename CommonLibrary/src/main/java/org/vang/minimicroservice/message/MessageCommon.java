package org.vang.minimicroservice.message;

import org.vang.minimicroservice.configmain.InitializeMessage;

import java.text.MessageFormat;
import java.util.Properties;

public class MessageCommon {

    private static final InitializeMessage initializeMessage = InitializeMessage.getInstance();

    private static Properties properties = null;

    static {
        properties = initializeMessage.getProperties();
    }

    public static String getMessage(String key) {
        return properties.getProperty(key);
    }

    public static String getMessage(String key, String[] params) {
        return MessageFormat.format(properties.getProperty(key), params);
    }
}