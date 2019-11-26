package lk.ijse.dep.pos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class HibernateConfig {
    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource){
        LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
        lsfb.setDataSource(dataSource);
        lsfb.setPackagesToScan("lk.ijse.dep.pos.entity");
        lsfb.setHibernateProperties(hibernateProperties());
        return lsfb;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dmds = new DriverManagerDataSource();
        dmds.setDriverClassName("com.mysql.jdbc.Driver");
        dmds.setUrl("jdbc:mysql://localhost:3306/DEP4HibernatePOS?createDatabaseIfNotExist=true");
        dmds.setUsername("root");
        dmds.setPassword("root");
        return dmds;
    }

    private Properties hibernateProperties(){
        Properties properties = new Properties();
        properties.put("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.show_sql",true);
        properties.put("hibernate.hbm2ddl.auto","update");
        properties.put("hibernate.allow_refresh_detached_entity",false);
        return properties;

    }
}
