package uz.pdp.configuration.database;

import lombok.Getter;
import lombok.ToString;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@Getter
@ToString
public class DatabaseProperties {
    private final String url = "jdbc:postgresql://localhost:5432/techno_store";
    private final String driver = "org.postgresql.Driver";
    private final String username = "postgres";
    private final String password = "ismoil_0709";
}
