package lk.ijse.dep.pos.business.custom.impl;

import lk.ijse.dep.pos.business.custom.OrderBO;
import lk.ijse.dep.pos.dao.custom.*;
import lk.ijse.dep.pos.db.HibernateUtil;
import lk.ijse.dep.pos.dto.OrderDTO;
import lk.ijse.dep.pos.dto.OrderDTO2;
import lk.ijse.dep.pos.dto.OrderDetailDTO;
import lk.ijse.dep.pos.entity.*;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class OrderBOImpl implements OrderBO {

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private OrderDetailDAO orderDetailDAO;
    @Autowired
    private ItemDAO itemDAO;
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private QueryDAO queryDAO;

    @Override
    public int getLastOrderId() throws Exception {
        return orderDAO.getLastOrderId();
    }

    @Override
    public void placeOrder(OrderDTO order) throws Exception {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            itemDAO.setSession(session);
            orderDAO.setSession(session);
            orderDetailDAO.setSession(session);
            customerDAO.setSession(session);
            session.beginTransaction();

            int oId = order.getId();
            orderDAO.save(new Order(oId, new java.sql.Date(new Date().getTime()),
                    session.load(Customer.class, order.getCustomerId())));

            for (OrderDetailDTO orderDetail : order.getOrderDetails()) {
                orderDetailDAO.save(new OrderDetail(oId, orderDetail.getCode(),
                        orderDetail.getQty(), orderDetail.getUnitPrice()));

                Item item = itemDAO.find(orderDetail.getCode());
                item.setQtyOnHand(item.getQtyOnHand() - orderDetail.getQty());
                itemDAO.update(item);

            }

            session.getTransaction().commit();

        }
    }

    @Override
    public List<OrderDTO2> getOrderInfo(String query) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            queryDAO.setSession(session);
            session.beginTransaction();
            List<CustomEntity> ordersInfo = queryDAO.getOrdersInfo(query + "%");
            List<OrderDTO2> dtos = new ArrayList<>();
            for (CustomEntity info : ordersInfo) {
                dtos.add(new OrderDTO2(info.getOrderId(),
                        info.getOrderDate(), info.getCustomerId(), info.getCustomerName(), info.getOrderTotal()));
            }
            session.getTransaction().commit();
            return dtos;
        }
    }
}
