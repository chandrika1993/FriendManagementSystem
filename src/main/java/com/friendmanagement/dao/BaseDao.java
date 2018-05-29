package com.friendmanagement.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * <PRE>
 * Class name       : BaseDao
 * Description      : This class is used manage object as entity to interact with database and service layer.
 * Author           : Capgemini.
 * </PRE>
 */
@Transactional
public class BaseDao<E> {

    @PersistenceContext
    protected EntityManager entityManager;

    public BaseDao() {
        /* Do nothing */
    }

    /**
     * @param entityManager the entityManager to set
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
