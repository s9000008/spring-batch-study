package com.demo.batch.provider;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.batch.item.database.orm.JpaQueryProvider;
import org.springframework.stereotype.Component;

@Component
public class OrdersProvider implements JpaQueryProvider{

	@PersistenceContext
    private EntityManager entityManager;

    @Override
    public Query createQuery() {
        return entityManager.createQuery("SELECT o FROM Orders o WHERE o.status = 'paid'");
    }

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
