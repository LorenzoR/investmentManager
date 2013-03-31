package org.investmentManager.persistence.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.investmentManager.model.Stock;
import org.investmentManager.persistence.StockDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@SuppressWarnings("unchecked")
public class StockDaoImpl extends AbstractHibernateDAO<Stock> implements
		StockDao {

	@Resource
	private SessionFactory sessionFactory;
	
	private final Class className = Stock.class;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected StockDaoImpl() {
		super(Stock.class);
	}

	@Override
	public List<String> getStocksName() {
		return (List<String>) super
				.getCurrentSession()
				.createCriteria(this.className)
				.setProjection(
						Projections.distinct(Projections.projectionList().add(
								Projections.property("name")))).list();
	}

	@Override
	public List<Stock> getStocks() {
		return super.getAll();
	}

	@Override
	public Long insertStock(Stock stock) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stock> getStocks(String name, int first, int count) {
		return (List<Stock>) super.getCurrentSession()
				.createCriteria(this.className)
				.add(Restrictions.ilike("name", name)).setFirstResult(first)
				.setMaxResults(count).list();
	}

}
