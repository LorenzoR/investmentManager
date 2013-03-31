package org.investmentManager.persistence.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.investmentManager.model.Investment;
import org.investmentManager.model.Stock;
import org.investmentManager.persistence.InvestmentDao;
import org.investmentManager.persistence.StockDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@SuppressWarnings("unchecked")
public class InvestmentDaoImpl extends AbstractHibernateDAO<Investment> implements
		InvestmentDao {

	@Resource
	private SessionFactory sessionFactory;

	private final Class className = Investment.class;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected InvestmentDaoImpl() {
		super(Investment.class);
	}

	@Override
	public List<String> getInvestmentsName() {
		return (List<String>) super
				.getCurrentSession()
				.createCriteria(this.className)
				.setProjection(
						Projections.distinct(Projections.projectionList().add(
								Projections.property("name")))).list();
	}

	@Override
	public List<Investment> getInvestments() {
		return super.getAll();
	}

	@Override
	public Long insertInvestment(Investment investment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Investment> getInvestments(String name, int first, int count) {
		return (List<Investment>) super.getCurrentSession()
				.createCriteria(this.className)
				.add(Restrictions.ilike("name", name)).setFirstResult(first)
				.setMaxResults(count).list();
	}

}
