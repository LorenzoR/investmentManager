package org.investmentManager.persistence;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.investmentManager.model.Account;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(locations="config-spring-hibernate.xml")
@ContextConfiguration(locations = { "file:///D:/workspace/InvestmentAnalyzer/investmentManager/src/main/resources/org/investmentManager/spring/applicationContext.xml" })
public class PersistenceTest {

	@Autowired
	private SessionFactory sessionFactory;

	@Test
	public void retrieveAccount() {
		Session session = sessionFactory.openSession(); // not part of a
														// transaction, so we
														// need to open a
														// session manually
		Query query = session.createQuery("from Account a where a.id=:id")
				.setInteger("id", 1);
		Account a = (Account) query.uniqueResult();
		session.close();
		Assert.assertEquals(a.getCashBalance(), 2500.0, 0.01);
	}

	@Test
	@Transactional
	public void updateAccount() {
		final String newName = "foo";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Account a where a.id=:id")
				.setInteger("id", 1);
		Account a = (Account) query.uniqueResult();
		a.setName(newName);
//		session.flush();
//		
//		query = session.createQuery("from Account a where a.id=:id")
//				.setInteger("id", 1);
//		a = (Account) query.uniqueResult();
//		session.close();
		Assert.assertEquals(a.getName(), newName);
		
	}

}