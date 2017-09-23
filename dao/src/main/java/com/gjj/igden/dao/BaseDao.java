package com.gjj.igden.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseDao {

	@Autowired
	protected SessionFactory sessionFactory;

	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Transactional
	public <T> String save(final T o) {
		return (String) getSession().save(o);
	}

	@Transactional
	public void delete(final Object object) {
		getSession().delete(object);
	}

	/***/
	@Transactional
	public <T> T get(final Class<T> type, final Long id) {
		return (T) getSession().get(type, id);
	}

	@Transactional
	public <T> T merge(final T o) {
		return (T) getSession().merge(o);
	}
	
	@Transactional
	public <T> void update(final T o) {
		getSession().update(o);
	}

	@Transactional
	public <T> void saveOrUpdate(final T o) {
		getSession().saveOrUpdate(o);
	}

	@Transactional
	public <T> List<T> getAll(final Class<T> type) {
		final Session session = getSession();
		final Criteria crit = session.createCriteria(type);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return crit.list();
	}
	
}
