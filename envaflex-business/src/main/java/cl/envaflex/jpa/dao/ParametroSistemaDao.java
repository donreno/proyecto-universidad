package cl.envaflex.jpa.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cl.envaflex.jpa.model.ParametroSistema;

@Repository(value=ParametroSistemaDao.BEAN_NAME)
public class ParametroSistemaDao extends GenericHibernateDAO<ParametroSistema> {
	
	public static final String BEAN_NAME = "parametroSistemaDAO";
	
	@Autowired
	public ParametroSistemaDao(@Qualifier(SESSION_FACTORY) SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	private static final String FIND_ALL_PARAMETRO_SISTEMA = "FROM ParametroSistema";
	
	@SuppressWarnings("unchecked")
	public List<ParametroSistema> findAll(){
		return this.getHibernateTemplate().find(FIND_ALL_PARAMETRO_SISTEMA);
	}
	
	public ParametroSistema findParametroByNombre(String nombre){
		Criteria crit = getSession().createCriteria(ParametroSistema.class);
		crit.add(Restrictions.eq("nombreParametro", nombre));
		return (ParametroSistema) crit.uniqueResult();
	}
	
}
