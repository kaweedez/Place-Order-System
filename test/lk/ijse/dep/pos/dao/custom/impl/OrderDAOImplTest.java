package lk.ijse.dep.pos.dao.custom.impl;

import lk.ijse.dep.pos.AppInitializer;
import lk.ijse.dep.pos.business.custom.OrderBO;
import lk.ijse.dep.pos.dao.custom.OrderDAO;

class OrderDAOImplTest {

    public static void main(String[] args) throws Exception {
        new OrderDAOImplTest().existsByCustomerId();
    }

    void existsByCustomerId() throws Exception {
        OrderDAO dao = AppInitializer.ctx.getBean(OrderDAO.class);
        System.out.println(dao.existsByCustomerId("C101"));
    }
}
