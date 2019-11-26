package lk.ijse.dep.pos.db;

import lk.ijse.dep.crypto.DEPCrypt;
import lk.ijse.dep.pos.entity.Customer;
import lk.ijse.dep.pos.entity.Item;
import lk.ijse.dep.pos.entity.Order;
import lk.ijse.dep.pos.entity.OrderDetail;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();
    private static String username;
    private static String password;
    private static String database;
    private static String port;
    private static String ip;


    private static SessionFactory buildSessionFactory(){
        Properties properties = new Properties();
        username =DEPCrypt.decode(properties.getProperty("hibernate.connection.username"),"dep4");
        password = DEPCrypt.decode(properties.getProperty("hibernate.connection.password"),"dep4");
        database = properties.getProperty("ijse.dep.db");
        port = properties.getProperty("ijse.dep.port");
        ip = properties.getProperty("ijse.dep.ip");

        // (1)
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .loadProperties("resources/application.properties")
                .applySetting("hibernate.connection.username", username)
                .applySetting("hibernate.connection.password",password)
                .build();

        // (2)
        Metadata metadata = new MetadataSources(standardRegistry)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(OrderDetail.class)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();

        // (3)
        return metadata.getSessionFactoryBuilder()
                .build();
    }

    public static String getUsername(){
        return username;
    }

    public static String getPassword(){
        return password;
    }

    public static String getDatabase(){
        return database;
    }

    public static String getPort(){
        return  port;
    }

    public static String getIp(){
        return ip;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
