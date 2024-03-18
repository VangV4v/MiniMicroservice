package org.vang.minimicroservice.configmain;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * SinglePattern
 */
public class InitializeMessage {

    @Getter
    private Properties properties = new Properties();

    private static final Logger LOGGER = LogManager.getLogger(InitializeMessage.class);
    private static final InitializeMessage INSTANCE = new InitializeMessage();

    private InitializeMessage() {

        try {
            String fileName = "message.properties";
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = loader.getResourceAsStream(fileName);
            this.properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static InitializeMessage getInstance() {
        return INSTANCE;
    }
}