package aiven.io.kafka_executor.config.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


import java.util.Properties;

@Configuration
@NoArgsConstructor
@Data
public class ConnectionConfig {
    @Value("${kafka_executor.host}")
    private String host;
    @Value("${kafka_executor.port}")
    private String port;

    @Value("${cert_pass}")
    private String cert_password;
    @Value("${kafka_executor.truststore_location}")
    private String truststore_location;
    @Value("${kafka_executor.keystore_location}")
    private String keystore_location;
    @Value("${kafka_executor.schema_registry_host}")
    private String schemaRegistryHost;
    @Value("${kafka_executor.schema_registry_port}")
    private String schemaRegistryPort;
    @Value("${kafka_executor.schema_registry_user}")
    private String schemaRegistryUser;
    @Value("${kafka_executor.schema_registry_password}")
    private String schemaRegistryPassword;


    public Properties connectionProperties(){
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", host + ":" + port);
        properties.setProperty("security.protocol", "SSL");
        properties.setProperty("ssl.truststore.location", truststore_location);
        properties.setProperty("ssl.truststore.password", cert_password);
        properties.setProperty("ssl.keystore.type", "PKCS12");
        properties.setProperty("ssl.keystore.location", keystore_location);
        properties.setProperty("ssl.keystore.password", cert_password);
        properties.setProperty("ssl.key.password", cert_password);
        return properties;
    }

    public Properties connectionWithSchemaRegistryProperties(){
        Properties properties = connectionProperties();
        properties.setProperty("schema.registry.url", "https://" + schemaRegistryHost + ":" + schemaRegistryPort);
        properties.setProperty("basic.auth.credentials.source", "USER_INFO");
        properties.setProperty("basic.auth.user.info", schemaRegistryUser + ":" + schemaRegistryPassword);

        return properties;
    }


}
