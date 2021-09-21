package org.acm.store.configuration;

import org.acm.store.model.DataBase;
import org.acm.store.model.cart.Cart;
import org.acm.store.model.category.Category;
import org.acm.store.model.comment.Comment;
import org.acm.store.model.product.Product;
import org.acm.store.model.user.User;
import org.acm.store.model.user.admin.Admin;
import org.acm.store.model.user.customer.Costumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {

    @Value("${db.driver}")
    private String DRIVER;

    @Value("${db.url}")
    private String URL;

    @Value("${db.username}")
    private String USERNAME;

    @Value("${db.password}")
    private String PASSWORD;

    @Value("${hibernate.dialect}")
    private String DIALECT;

    /*@Value("${hibernate.show_sql}")
    private String SHOW_SQL;

    @Value("${hibernate.format_sql}")
    private String FORMAT_SQL;*/

    @Value("${hibernate.hbm2ddl.auto}")
    private String HBM2DDL_AUTO;

    @Value("${hibernate.connection.pool_size}")
    private String POOL_SIZE;

    @Value("${hibernate.current_session_context_class}")
    private String SESSION;

    @Value("${hibernate.connection.verifyServerCertificate}")
    private String CERTIFICATE;

    @Value("${hibernate.connection.useSSL}")
    private String USE_SSL;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(DRIVER);
        driverManagerDataSource.setUrl(URL);
        driverManagerDataSource.setUsername(USERNAME);
        driverManagerDataSource.setPassword(PASSWORD);
        return driverManagerDataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setPackagesToScan("{org.acm.store.model}");
        localSessionFactoryBean.setAnnotatedClasses(Admin.class, Comment.class, Product.class, Category.class,
                Cart.class, Costumer.class, User.class, DataBase.class);
        Properties properties = new Properties();
        properties.put("hibernate.dialect", DIALECT);
        /*properties.put("hibernate.show_sql", SHOW_SQL);
        properties.put("hibernate.format_sql", FORMAT_SQL);*/
        properties.put("hibernate.hbm2ddl.auto", HBM2DDL_AUTO);
        properties.put("hibernate.connection.pool_size", POOL_SIZE);
        properties.put("hibernate.current_session_context_class", SESSION);
        properties.put("hibernate.connection.verifyServerCertificate", CERTIFICATE);
        properties.put("hibernate.connection.useSSL", USE_SSL);
        localSessionFactoryBean.setHibernateProperties(properties);
        return localSessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory().getObject());
        return hibernateTransactionManager;
    }
}
