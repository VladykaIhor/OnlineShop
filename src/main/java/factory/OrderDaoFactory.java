package factory;

import dao.OrderDao;
import dao.impl.hib.OrderDaoHibImpl;

public class OrderDaoFactory {

    private static OrderDao orderDaoInstance;

    public OrderDaoFactory() {
    }

    public static OrderDao getInstance() {
        if (orderDaoInstance == null) {
            orderDaoInstance = new OrderDaoHibImpl();
        }
        return orderDaoInstance;
    }
}
