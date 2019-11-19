package lk.ijse.dep.pos.dao.custom.impl;

import lk.ijse.dep.pos.dao.CrudDAOImpl;
import lk.ijse.dep.pos.dao.custom.CustomerDAO;
import lk.ijse.dep.pos.entity.Customer;

import javax.persistence.EntityManager;

public class CustomerDAOImpl extends CrudDAOImpl<Customer, String> implements CustomerDAO {

    private EntityManager entityManager;

    @Override
    public String getLastCustomerId() throws Exception {

        return (String) entityManager.createNativeQuery("SELECT customerId FROM Customer ORDER BY customerId DESC LIMIT 1").getSingleResult();
    }

}
