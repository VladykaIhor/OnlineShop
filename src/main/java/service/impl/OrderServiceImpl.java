package service.impl;

import dao.OrderDao;
import factory.OrderDaoFactory;
import model.Order;
import model.User;
import service.OrderService;

public class OrderServiceImpl implements OrderService {

    private static OrderDao orderDao = OrderDaoFactory.getInstance();

    @Override
    public void addOrder(Order order) {
        orderDao.addOrder(order);
    }

    @Override
    public Long getOrderIdByUser(User user) {
        return orderDao.getLastOrderForUser(user).get().getId();
    }
}
