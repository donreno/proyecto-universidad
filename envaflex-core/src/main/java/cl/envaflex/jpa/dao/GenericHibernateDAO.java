package cl.envaflex.jpa.dao;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class GenericHibernateDAO<T> extends HibernateDaoSupport {
	
	protected static final String SESSION_FACTORY = "sessionFactory";

    public GenericHibernateDAO(SessionFactory 
            sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    public void insertar(Object o) {
        this.getHibernateTemplate().saveOrUpdate(o);
    }
    
    public void actualizar(Object o) {
        this.getHibernateTemplate().saveOrUpdate(o);
    }

    public void eliminar(Object o) {
        this.getHibernateTemplate().delete(o);
    }
    
    public abstract List<T> findAll();

}
