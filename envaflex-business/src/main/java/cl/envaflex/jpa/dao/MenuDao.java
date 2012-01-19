package cl.envaflex.jpa.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cl.envaflex.jpa.model.Menu;
@Repository(value="menuDAO")
public class MenuDao extends GenericHibernateDAO<Menu> {
	
	@Autowired
	public MenuDao(@Qualifier(SESSION_FACTORY) SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	private static final String FIND_ALL_MENU = "FROM Menu";
	
	@SuppressWarnings("unchecked")
	public List<Menu> findAll(){
		return this.getHibernateTemplate().find(FIND_ALL_MENU);
	}
	
}
