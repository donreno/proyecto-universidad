package cl.envaflex.jpa.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cl.envaflex.jpa.model.Empleado;

@Repository(value=EmpleadoDao.BEAN_NAME)
public class EmpleadoDao extends GenericHibernateDAO<Empleado> {
	
	public static final String BEAN_NAME = "empleadoDAO";
	
	@Autowired
	public EmpleadoDao(@Qualifier(SESSION_FACTORY) SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	private static final String FIND_ALL_EMPLEADOS = "FROM Empleado";
	
	@SuppressWarnings("unchecked")
	public List<Empleado> findAll(){
		return this.getHibernateTemplate().find(FIND_ALL_EMPLEADOS);
	}
	
	public Empleado findUsuario(String username,String password){
		Criteria crit = getSession().createCriteria(Empleado.class);
		crit.add(Restrictions.eq("user", username));
		crit.add(Restrictions.eq("password", password));
		Empleado emp = null;
		emp = (Empleado) crit.uniqueResult();
		return emp;
	}


	
	

}
