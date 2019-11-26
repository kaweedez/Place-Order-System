package lk.ijse.dep.pos;

import lk.ijse.dep.crypto.DEPCrypt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@PropertySource("file:${user.dir}/resources/application.properties")
@EnableTransactionManagement
public class JPAConfig {

    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource ds, JpaVendorAdapter jpaAdapter){
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setDataSource(ds);
        lcemfb.setJpaVendorAdapter(jpaAdapter);
        return lcemfb;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(env.getRequiredProperty("javax.persistence.jdbc.driver"));
        ds.setUrl(env.getRequiredProperty("javax.persistence.jdbc.url"));
        ds.setUsername(DEPCrypt.decode(env.getRequiredProperty("javax.persistence.jdbc.user"), "dep4"));
        ds.setPassword(DEPCrypt.decode(env.getRequiredProperty("javax.persistence.jdbc.password"), "dep4"));
        return ds;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter jpaAdapter = new HibernateJpaVendorAdapter();
        jpaAdapter.setDatabase(Database.MYSQL);
        jpaAdapter.setDatabasePlatform(env.getRequiredProperty("hibernate.dialect"));
        jpaAdapter.setGenerateDdl(env.getRequiredProperty("hibernate.hbm2ddl.auto").equals("update"));
        jpaAdapter.setShowSql(env.getRequiredProperty("hibernate.show_sql", Boolean.class));
        return jpaAdapter;
    }

    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        return new JpaTransactionManager(emf);
    }
}