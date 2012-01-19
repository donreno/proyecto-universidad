package cl.envaflex.util;

import java.io.File;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Clase de utilidad para obtener la sesion de hibernate.
 * 
 */
public class HibernateUtils {

    private static final SessionFactory sessionFactory;
    private static final String HIBERNATE_CONFIG_FILE = "hibernate.cfg.xml";

    static {
        try {
            sessionFactory = new AnnotationConfiguration().configure(
                    new File(HIBERNATE_CONFIG_FILE)).buildSessionFactory();
        } catch (Throwable ex) {
            // Log exception!
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }
}
