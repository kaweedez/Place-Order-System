package lk.ijse.dep.pos.dao.custom.impl;

import lk.ijse.dep.pos.dao.CrudDAOImpl;
import lk.ijse.dep.pos.dao.custom.CustomerDAO;

import lk.ijse.dep.pos.dao.custom.ItemDAO;
import lk.ijse.dep.pos.entity.Item;

import javax.persistence.EntityManager;
import java.sql.ResultSet;

public class ItemDAOImpl extends CrudDAOImpl<Item, String> implements ItemDAO {

    private EntityManager entityManager;

    @Override
    public String getLastItemCode() throws Exception {
        return (String) entityManager.createNativeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1").getSingleResult();

    }

}
