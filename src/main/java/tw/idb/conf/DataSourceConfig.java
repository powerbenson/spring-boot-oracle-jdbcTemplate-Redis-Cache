package tw.idb.conf;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig extends DataSourceAutoConfiguration {

    @Bean(name = "namedParamJdbc")
    public NamedParameterJdbcTemplate namedParameterJdbc(DataSource datasource) {
        return new NamedParameterJdbcTemplate(datasource);
    }
}
