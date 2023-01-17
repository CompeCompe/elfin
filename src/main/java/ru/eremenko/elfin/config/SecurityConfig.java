package ru.eremenko.elfin.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author eremenko
 */
@Configuration
public class SecurityConfig {

    @Bean(name = "restStrongEncryptor")
    public StandardPBEStringEncryptor createStrongEncryptor() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
        encryptor.setPassword("HUZHmV!eQcNdr-1Y");
        return encryptor;
    }
}
