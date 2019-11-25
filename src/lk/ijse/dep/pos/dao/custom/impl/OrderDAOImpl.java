package lk.ijse.dep.pos.dao.custom.impl;

import lk.ijse.dep.pos.dao.CrudDAOImpl;
import lk.ijse.dep.pos.dao.custom.CustomerDAO;
import lk.ijse.dep.pos.dao.custom.OrderDAO;
import lk.ijse.dep.pos.entity.Customer;
import lk.ijse.dep.pos.entity.Order;

import javax.persistence.EntityManager;
import java.sql.ResultSet;

public class OrderDAOImpl extends CrudDAOImpl<Order, Integer> implements OrderDAO {

    private EntityManager entityManager;

    @Override
    public int getLastOrderId() throws Exception {
        return (Integer) entityManager.createNativeQuery("SELECT id FROM `Order` ORDER BY id DESC LIMIT 1").getSingleResult();

    }

    @Override
    public boolean existsByCustomerId(String customerId) throws Exception {
        return entityManager.createNativeQuery("SELECT * FROM `Order` WHERE id=?1")
                .setParameter(1, customerId).getResultList() != null;
    }

}
