package org.acm.store.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.MetadataSource;
import org.hibernate.service.ServiceRegistry;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

@org.springframework.context.annotation.Configuration
@EntityScan({"org.acm.store.model"})
public class HibernateUtil {

    private static StandardServiceRegistry standardServiceRegistry;
    private static SessionFactory sessionFactory = buildSessionFactory();

    /*public static Session getCurrentSessionFromConfig() {
        Configuration config = new Configuration();
        config.configure();
        sessionFactory = config.buildSessionFactory();
        return sessionFactory.getCurrentSession();
    }*/

    private static SessionFactory buildSessionFactory() {
        try {
            if (sessionFactory == null) {
                /*Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
                StandardServiceRegistry standardRegistry =
                        new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                MetadataSources sources = new MetadataSources(standardRegistry);
                Metadata metaData = sources.getMetadataBuilder().build();
                sessionFactory = metaData.getSessionFactoryBuilder().build();*/

                /*Configuration configuration = new Configuration();
                configuration.addResource("hibernate.cfg.xml");
                configuration.configure();
                System.out.println(configuration.getProperties());
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);*/

                /*ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
                Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();*/

                /*Configuration config = new Configuration();
                config.configure("hibernate.cfg.xml");
                sessionFactory = config.buildSessionFactory();*/

                standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
                MetadataSources metadataSource = new MetadataSources(standardServiceRegistry);
                Metadata metadata = metadataSource.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            }
            return sessionFactory;
        }catch (Exception ex) {
            if (standardServiceRegistry != null){
                StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
            }
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            System.out.println(ex.getLocalizedMessage());
            throw new ExceptionInInitializerError(ex);
        } catch (Throwable ex) {
            if (standardServiceRegistry != null){
                StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
            }
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            System.out.println(ex.getLocalizedMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutDown() {
        getSessionFactory().close();
        if (standardServiceRegistry != null){
            StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
        }
    }
}
