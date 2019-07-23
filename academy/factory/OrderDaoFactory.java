package mate.academy.factory;

import mate.academy.dao.OrderDao;
import mate.academy.dao.impl.OrderDaoImpl;
import mate.academy.service.impl.OrderServiceImpl;

public class OrderDaoFactory {

    private static OrderDao orderDaoInstance;

    public OrderDaoFactory() {
    }

    public static OrderDao getInstance() {
        if (orderDaoInstance == null) {
            orderDaoInstance = new OrderDaoImpl();
        }
        return orderDaoInstance;
    }
}
